package com.galaxy.bigdata.jmx.modelBean

import com.galaxy.bigdata.jmx.Hello
import javax.management.{MBeanOperationInfo, MBeanParameterInfo}
import javax.management.modelmbean.{ModelMBeanAttributeInfo, ModelMBeanInfo, ModelMBeanInfoSupport, ModelMBeanOperationInfo, RequiredModelMBean}

object ModelMBeanUtils {

  val READABLE = true
  val WRITABLE = true
  val BOOLEAN = true

  val STRING_CLASS = "java.lang.String"

  def createModelerMBean: RequiredModelMBean = {
    val model = new RequiredModelMBean()
    model.setManagedResource(new Hello, "ObjectReference")
    val info = createModelMBeanInfo
    model.setModelMBeanInfo(info)
    model
  }

  def createModelMBeanInfo: ModelMBeanInfo = {

    //////////////////////////////////////////////////////////////////
    //                        属性                                        //
    //////////////////////////////////////////////////////////////////
    // 构造name属性信息
    import javax.management.modelmbean.DescriptorSupport
    val portAttrDesc = new DescriptorSupport
    portAttrDesc.setField("name", "Name");
    portAttrDesc.setField("descriptorType", "attribute");
    portAttrDesc.setField("displayName", "Name");
    portAttrDesc.setField("getMethod", "getName");
    portAttrDesc.setField("setMethod", "setName");

    val nameAttrInfo = new ModelMBeanAttributeInfo( //
      "Name", // 属性名
      STRING_CLASS, //属性类型
      "people name", // 描述文字
      READABLE, WRITABLE, !BOOLEAN, // 读写
      portAttrDesc // 属性描述
    )

    //////////////////////////////////////////////////////////////////
    //                        方法                                        //
    //////////////////////////////////////////////////////////////////
    // 构造 getName操作描述符信息
    val getStateDesc = new DescriptorSupport(
      "name=getName",
      "descriptorType=operation",
      "class=com.test.jmx.modelBean.Hello",
      "role=operation")
    val getName = new ModelMBeanOperationInfo(
      "getName",
      "get name attribute",
      null,
      "java.lang.String",
      MBeanOperationInfo.ACTION,
      getStateDesc
    )

    // 构造 setName操作描述符信息
    val setStateDesc = new DescriptorSupport(
      "name=setName",
      "descriptorType=operation",
      "class=com.test.jmx.modelBean.Hello",
      "role=operation")

    val setStateParms = Array[MBeanParameterInfo](new MBeanParameterInfo(
      "name", "java.lang.String", "new name value"))

    val setName = new ModelMBeanOperationInfo( //
      "setName", //
      "set name attribute", //
      setStateParms, //
      "void", //
      MBeanOperationInfo.ACTION, //
      setStateDesc //
    )

    // 构造 printHello()操作的信息
    val print1Info = new ModelMBeanOperationInfo(//
      "printHello", //
      null, //
      null, //
      "void", //
      MBeanOperationInfo.INFO, //
      null //
    )

    // 构造printHello(String whoName)操作信息
    val param2 = new Array[MBeanParameterInfo](1)
    param2(0) = new MBeanParameterInfo("whoName", STRING_CLASS, "say hello to who")
    val print2Info = new ModelMBeanOperationInfo(//
      "printHello", //
      null,//
      param2,//
      "void", //
      MBeanOperationInfo.INFO, //
      null//
    )

    //////////////////////////////////////////////////////////////////
    //                        最后总合                                    //
    //////////////////////////////////////////////////////////////////
    // create ModelMBeanInfo
    val mbeanInfo = new ModelMBeanInfoSupport(
      classOf[RequiredModelMBean].getName, // MBean类
      null, // 描述文字
      Array[ModelMBeanAttributeInfo](nameAttrInfo),// 所有的属性信息（数组）只有一个属性
      null, // 所有的构造函数信息
      Array[ModelMBeanOperationInfo](getName,setName,print1Info,print2Info),// 所有的操作信息（数组）
      null, // 所有的通知信息(本例无)
      null//MBean描述
    )
    mbeanInfo
  }
}
