package com.galaxy.scala.conf

import java.io.InputStream

import com.ecwid.consul.v1.ConsulClient
import javax.annotation.{PostConstruct, PreDestroy}
import org.cfg4j.source.context.propertiesprovider.{JsonBasedPropertiesProvider, PropertiesProviderSelector, PropertyBasedPropertiesProvider, YamlBasedPropertiesProvider}
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties
import org.springframework.cloud.consul.serviceregistry.{ConsulRegistration, ConsulServiceRegistry}
import org.springframework.core.env.Environment
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import org.yaml.snakeyaml.Yaml

import scala.io.Source

/**
  * Created by wangpeng
  * Date: 2018/5/28
  * Time: 10:09
  */
@Component
class PropertyReadHelper {

  @Autowired
  private var environment: Environment = _

  @Autowired
  private var consulRegistration: ConsulRegistration = _

  @Autowired
  private var consulServiceRegistry: ConsulServiceRegistry = _

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

  @PreDestroy
  def destroy(): Unit = {
    this.consulServiceRegistry.deregister(consulRegistration)
//    val response = this.consulClient.agentServiceDeregister(consulRegistration.getInstanceId)
//    println("Deregistering service with consul: " + response.toString)
  }
}
