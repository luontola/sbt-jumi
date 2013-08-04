// Copyright © 2013, Esko Luontola <www.orfjackal.net>
// This software is released under the Apache License 2.0.
// The license text is at http://www.apache.org/licenses/LICENSE-2.0

package fi.jumi.sbt

import org.junit._
import org.junit.Assert._
import fi.jumi.core.config._
import java.lang.reflect.{Method, Modifier}
import sbt.{TaskKey, SettingKey, Scoped}
import sbt.Def.Setting

class JumiPluginTest {

  @Test
  def has_key_for_each_suite_parameter() {
    val expectedParameters = getInstanceFieldNames(classOf[SuiteConfiguration]) map formatKeyName

    expectedParameters.foreach {
      name =>
        assertHasKey(JumiPlugin.getClass, name)
    }
  }

  @Test
  def has_key_for_each_daemon_parameter() {
    val hiddenParameters = Set("launcherPort", "logActorMessages")
    val expectedParameters = getInstanceFieldNames(classOf[DaemonConfiguration]) -- hiddenParameters map formatKeyName

    expectedParameters.foreach {
      name =>
        assertHasKey(JumiPlugin.getClass, name)
    }
  }

  @Test
  def all_keys_have_a_default_value_in_settings() {
    val keyMethods = JumiPlugin.getClass.getMethods.toSeq filter isSettingOrTaskKey
    val keysByName = keyMethods.map(m => m.getName -> m.invoke(JumiPlugin).asInstanceOf[Scoped])
    val settingsByKey = JumiPlugin.jumiSettings.map(s => (s.key.key.label, s)).toMap[String, Setting[_]]

    keysByName.foreach {
      case (name, key) =>
        assertNotNull("not present in settings: " + name, settingsByKey.getOrElse(name, null))
    }
  }


  // helpers

  private def assertHasKey(clazz: Class[_], name: String) {
    try {
      val method = clazz.getMethod(name)
      val returnType = method.getReturnType
      if (returnType != classOf[SettingKey[_]] && returnType != classOf[TaskKey[_]]) {
        fail("property " + clazz.getName + "." + name + " had wrong type: " + returnType)
      }
    } catch {
      case e: NoSuchMethodException => fail("no such property: " + clazz.getName + "." + name)
    }
  }

  private def formatKeyName(property: String): String =
    ("jumi" + property.capitalize).replaceFirst("^jumiJumi", "jumi").replaceFirst("ClassPath", "Classpath")

  private def getInstanceFieldNames(clazz: Class[_]): Set[String] =
    clazz.getDeclaredFields.toSet filterNot (f => Modifier.isStatic(f.getModifiers)) map (_.getName)

  private def isSettingOrTaskKey(method: Method): Boolean =
    classOf[Scoped].isAssignableFrom(method.getReturnType)
}
