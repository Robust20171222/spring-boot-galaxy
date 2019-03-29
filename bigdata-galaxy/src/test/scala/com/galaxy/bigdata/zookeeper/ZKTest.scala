package com.galaxy.bigdata.zookeeper

import java.util
import java.util.concurrent.CountDownLatch

import org.apache.zookeeper.Watcher.Event.{EventType, KeeperState}
import org.apache.zookeeper.ZooDefs.Ids
import org.apache.zookeeper._
import org.apache.zookeeper.data.Stat
import org.junit.Test

/**
  * 测试Zookeeper基本功能
  *
  * Created by wangpeng
  * Date: 2019-03-07
  * Time: 20:31
  */
class ZKTest {

  val connectedSemaphore = new CountDownLatch(1)
  val host = "hadoop1:2181"
  val sessionTimeout = 5000
  var zk: ZooKeeper = _

  /**
    * 测试Zookeeper会话
    */
  @Test
  def testZK_Construct: Unit = {
    val zookeeper = new ZooKeeper(host, sessionTimeout, new Watcher {
      override def process(watchedEvent: WatchedEvent): Unit = {
        println("Receive watched event established.")
        if (KeeperState.SyncConnected == watchedEvent.getState) {
          connectedSemaphore.countDown()
        }
      }
    })
    println(zookeeper.getState)

    try {
      connectedSemaphore.await()
    } catch {
      case e: InterruptedException => e.printStackTrace()
    }

    println("ZooKeeper session established. ")
  }

  /**
    * 测试复用sessionId和sessionPasswd
    */
  @Test
  def testZK_Constructor_With_SID_PASSWD: Unit = {
    var zookeeper = new ZooKeeper(host, sessionTimeout, new Watcher {
      override def process(watchedEvent: WatchedEvent): Unit = {
        println("Receive watched event established.")
        if (KeeperState.SyncConnected == watchedEvent.getState) {
          connectedSemaphore.countDown()
        }
      }
    })
    connectedSemaphore.await()

    val sessionId = zookeeper.getSessionId
    val passwd = zookeeper.getSessionPasswd

    // 使用不正确的sessionId和passwd
    //    zookeeper = new ZooKeeper(host, sessionTimeout, new Watcher {
    //      override def process(watchedEvent: WatchedEvent): Unit = {
    //        println("Receive watched event established.")
    //        if (KeeperState.SyncConnected == watchedEvent.getState) {
    //          connectedSemaphore.countDown()
    //        }
    //      }
    //    }, 1l, "test".getBytes)

    // 使用正确的sessionId和passwd
    zookeeper = new ZooKeeper(host, sessionTimeout, new Watcher {
      override def process(watchedEvent: WatchedEvent): Unit = {
        println("Receive watched event established.")
        if (KeeperState.SyncConnected == watchedEvent.getState) {
          connectedSemaphore.countDown()
        }
      }
    }, sessionId, passwd)
  }

  /**
    * 测试ZK API创建节点，使用同步接口
    */
  @Test
  def testCreateAPIWithSync: Unit = {
    val zookeeper = new ZooKeeper(host, sessionTimeout, new Watcher {
      override def process(watchedEvent: WatchedEvent): Unit = {
        println("Receive watched event established.")
        if (KeeperState.SyncConnected == watchedEvent.getState) {
          connectedSemaphore.countDown()
        }
      }
    })

    connectedSemaphore.await()

    val path1 = zookeeper.create("/zk-test-ephemeral-", "".getBytes, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL)
    println("Success create znode: " + path1)

    val path2 = zookeeper.create("/zk-test-ephemeral-", "".getBytes, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL)
    println("Success create znode: " + path2)
  }

  /**
    * 使用异步API创建节点
    */
  @Test
  def testCreateAPIWithASync: Unit = {
    val zookeeper = new ZooKeeper(host, sessionTimeout, new Watcher {
      override def process(watchedEvent: WatchedEvent): Unit = {
        println("Receive watched event established.")
        if (KeeperState.SyncConnected == watchedEvent.getState) {
          connectedSemaphore.countDown()
        }
      }
    })
    connectedSemaphore.await()

    zookeeper.create("/zk-test-ephemeral-", "".getBytes, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new IStringCallback, "I am context")
    zookeeper.create("/zk-test-ephemeral-", "".getBytes, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new IStringCallback, "I am context")
    zookeeper.create("/zk-test-ephemeral-", "".getBytes, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, new IStringCallback, "I am context")

    Thread.sleep(Integer.MAX_VALUE)
  }

  /**
    * 使用同步API获取子节点列表
    */
  @Test
  def testGetChildrenWithSync: Unit = {
    val path = "/zk-book"
    zk = new ZooKeeper(host, sessionTimeout, new Watcher {
      override def process(watchedEvent: WatchedEvent): Unit = {
        println("Receive watched event established.")
        if (KeeperState.SyncConnected == watchedEvent.getState) {
          if (EventType.None == watchedEvent.getType && null == watchedEvent.getPath) {
            connectedSemaphore.countDown()
          } else if (watchedEvent.getType == EventType.NodeChildrenChanged) {
            try {
              println(s"ReGet Child ${zk.getChildren(watchedEvent.getPath, true)}")
            } catch {
              case e: Exception => e.printStackTrace()
            }
          }
        }
      }
    })

    connectedSemaphore.await()

    zk.create(path, "".getBytes, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT)
    zk.create(path + "/c1", "".getBytes, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL)

    val childrenList = zk.getChildren(path, true)
    println(childrenList)

    zk.create(path + "/c2", "".getBytes, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL)

    Thread.sleep(Integer.MAX_VALUE)
  }

  /**
    * 使用异步API获取子节点列表
    */
  @Test
  def testGetChildrenWithASync: Unit = {
    val path = "/zk-book-3"
    zk = new ZooKeeper(host, sessionTimeout, new Watcher {
      override def process(watchedEvent: WatchedEvent): Unit = {
        println("Receive watched event established.")
        if (KeeperState.SyncConnected == watchedEvent.getState) {
          if (EventType.None == watchedEvent.getType && null == watchedEvent.getPath) {
            connectedSemaphore.countDown()
          } else if (watchedEvent.getType == EventType.NodeChildrenChanged) {
            try {
              println(s"ReGet Child ${zk.getChildren(watchedEvent.getPath, true)}")
            } catch {
              case e: Exception => e.printStackTrace()
            }
          }
        }
      }
    })

    connectedSemaphore.await()
    zk.create(path, "".getBytes, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT)
    zk.create(path + "/c1", "".getBytes, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL)
    zk.getChildren(path, true, new IChildren2Callback(), null)
    zk.create(path + "/c2", "".getBytes, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL)

    Thread.sleep(Integer.MAX_VALUE)
  }

  /**
    * 测试使用同步API更新数据
    */
  @Test
  def testSetDataWithSync: Unit = {
    val path = s"/zk-book${System.currentTimeMillis()}"

    zk = new ZooKeeper(host, sessionTimeout, new Watcher {
      override def process(watchedEvent: WatchedEvent): Unit = {
        if (KeeperState.SyncConnected == watchedEvent.getState) {
          if (EventType.None == watchedEvent.getType && null == watchedEvent.getPath) {
            connectedSemaphore.countDown()
          }
        }
      }
    })

    connectedSemaphore.await()

    this.zk.create(path, "123".getBytes, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL)
    this.zk.getData(path, true, null)

    val stat = this.zk.setData(path, "456".getBytes, -1)
    println(s"${stat.getCzxid}, ${stat.getMzxid}, ${stat.getVersion}")

    val stat2 = this.zk.setData(path, "456".getBytes, stat.getVersion)
    println(s"${stat2.getCzxid}, ${stat2.getMzxid}, ${stat2.getVersion}")

    try {
      this.zk.setData(path, "456".getBytes, stat.getVersion)
    } catch {
      case e: KeeperException => println(s"Error: ${e.code()}, ${e.getMessage}")
    }

    Thread.sleep(Integer.MAX_VALUE)
  }

  /**
    * 测试使用异步API更新数据
    */
  @Test
  def testSetDataWithASync: Unit = {
    val path = s"/zk-book-1025"

    zk = new ZooKeeper(host, sessionTimeout, new Watcher {
      override def process(watchedEvent: WatchedEvent): Unit = {
        if (KeeperState.SyncConnected == watchedEvent.getState) {
          if (EventType.None == watchedEvent.getType && null == watchedEvent.getPath) {
            connectedSemaphore.countDown()
          }
        }
      }
    })

    connectedSemaphore.await()

    val data = Array[Byte]((4096 * 1024 * 2).toByte)

    this.zk.create(path, data, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL)
    //    this.zk.setData(path, "456".getBytes, -1, new IStatCallBack, null)

    println(System.getProperty("jute.maxbuffer"))
  }
}

class IStringCallback extends AsyncCallback.StringCallback {

  /**
    * 回调函数
    *
    * @param rc   服务端响应码
    * @param path 节点路径
    * @param ctx  传入的参数值
    * @param name 在服务端创建的节点名
    */
  override def processResult(rc: Int, path: String, ctx: Any, name: String): Unit = {
    println(s"Create path result: $rc, $path, $ctx, real path name : $name")
  }
}

class IChildren2Callback extends AsyncCallback.Children2Callback {

  override def processResult(rc: Int, path: String, ctx: Any, children: util.List[String], stat: Stat): Unit = {
    println(s"Get Children znode result : [response code: $rc, param path: $path, ctx: $ctx, children list: $children, stat: $stat")
  }
}

class IStatCallBack extends AsyncCallback.StatCallback {
  override def processResult(rc: Int, s: String, o: Any, stat: Stat): Unit = {
    if (rc == 0) {
      println("Success")
    }
  }
}
