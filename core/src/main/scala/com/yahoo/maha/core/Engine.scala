// Copyright 2017, Yahoo Holdings Inc.
// Licensed under the terms of the Apache License 2.0. Please see LICENSE file in project root for terms.
package com.yahoo.maha.core

/**
 * Created by hiral on 10/5/15.
 */
sealed trait Engine

case object HiveEngine extends Engine {
  override def toString = "Hive"
}
case object OracleEngine extends Engine {
  override def toString = "Oracle"
}
case object DruidEngine extends Engine {
  override def toString = "Druid"
  val MAX_ALLOWED_ROWS: Int = 5000
}

object Engine {
  def from(s: String): Option[Engine] = {
    s.toLowerCase match {
      case "hive" => Option(HiveEngine)
      case "oracle" => Option(OracleEngine)
      case "druid" => Option(DruidEngine)
      case _ => None
    }
  }
}

sealed trait EngineRequirement {
  def engine: Engine
  def acceptEngine(engine: Engine) = engine == this.engine
}

trait WithHiveEngine extends EngineRequirement {
  final val engine: Engine = HiveEngine
}

trait WithOracleEngine extends EngineRequirement {
  final val engine: Engine = OracleEngine
}

trait WithDruidEngine extends EngineRequirement {
  final val engine: Engine = DruidEngine
}
