package com.galaxy.scala.conf

import java.io.InputStream

import javax.annotation.PostConstruct
import org.cfg4j.source.context.propertiesprovider.{JsonBasedPropertiesProvider, PropertiesProviderSelector, PropertyBasedPropertiesProvider, YamlBasedPropertiesProvider}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import org.yaml.snakeyaml.Yaml

import scala.collection.JavaConversions._

/**
  * Created by wangpeng
  * Date: 2018/5/28
  * Time: 10:09
  */
@Component
class PropertyReadHelper {

  @Autowired
  private var environment: Environment = _

  @PostConstruct
  def displayAllProperties: Unit = {
    val resource = new ClassPathResource("application.yml")
    val yaml = new Yaml

    val inputStream: InputStream = resource.getInputStream


//    val result: java.util.Map[String,Object] = yaml.load(inputStream).asInstanceOf[java.util.Map[String,Object]]
//
//    result.foreach(f => {
//      println("snakeyaml----" + f._1 + "----" + f._2)
//    })

    val propertiesProviderSelector = new PropertiesProviderSelector(new PropertyBasedPropertiesProvider, new YamlBasedPropertiesProvider, new JsonBasedPropertiesProvider)
    val provider = propertiesProviderSelector.getProvider(resource.getFilename)
    val ymlProperties = provider.getProperties(inputStream)

    println("cfg4j-----" + ymlProperties)
  }
}
