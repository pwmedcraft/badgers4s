package badgers4s.config

import com.typesafe.config.Config

final case class ApplicationConfiguration(port: Int)

object ApplicationConfiguration {
  def apply(config: Config): ApplicationConfiguration =
    ApplicationConfiguration(
      config.getInt("http.port")
    )
}
