package com.galaxy.test.basic

import java.util.concurrent.{ScheduledExecutorService, ScheduledThreadPoolExecutor, TimeUnit}

import org.junit.Test
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler

import scala.reflect.runtime.universe._
import scala.reflect._

/**
  * Scala反射测试
  *
  * @author pengwang
  * @date 2019/12/04
  */
class ReflectTest {

  val ru = scala.reflect.runtime.universe

  @Test
  def test1: Unit = {
    val res0 = typeTag[List[Int]]
    println(res0)
    println(res0.tpe)
    println(typeOf[List[Int]])
  }

  @Test
  def test2: Unit = {
    val list = List(1, 2, 3)
    val theType = getTypeTag(list).tpe
    println(theType)
    println(theType.declarations.take(10))
  }

  @Test
  def testClassTag: Unit = {
    val clsTag = classTag[List[Int]]
    println(clsTag.runtimeClass)
    val cls = classOf[List[Int]]
    println(cls)
  }

  /**
    * 运行时类型实例化
    */
  @Test
  def test3: Unit = {
    val m = ru.runtimeMirror(getClass.getClassLoader)
    println(m)
    val classPerson = ru.typeOf[Person].typeSymbol.asClass
    println(classPerson)
    val cm = m.reflectClass(classPerson)
    println(cm)
    val ctor = ru.typeOf[Person].declaration(ru.nme.CONSTRUCTOR).asMethod
    println(ctor)
    val ctorm = cm.reflectConstructor(ctor)
    val p = ctorm(1, "Mike")
  }

  var p1: Person = _

  /**
    * 运行时类成员访问
    */
  @Test
  def test4: Unit = {
    getPerson
    Thread.sleep(2000)
    println("运行时类成员访问")
    val m = ru.runtimeMirror(getClass.getClassLoader)
    val p = p1
    val nameTermSymb = ru.typeOf[Person].declaration(ru.newTermName("name")).asTerm
    val im = m.reflect(p)
    val nameFieldMirror = im.reflectField(nameTermSymb)
    while (true) {
      println(nameFieldMirror.get)
      Thread.sleep(5000)
    }
    nameFieldMirror.set("Jim")
    println(p.name)
  }

  def getPerson: Unit = {
    val executor = new ScheduledThreadPoolExecutor(5)
    executor.scheduleAtFixedRate(new Runnable {
      override def run(): Unit = {
        p1 = Person(1, "Mike--" + Thread.currentThread().getId)
      }
    }, 1, 2, TimeUnit.SECONDS)
  }

  def getTypeTag[T: ru.TypeTag](obj: T) = ru.typeTag[T]

}

case class Person(id: Int, name: String) {

}
