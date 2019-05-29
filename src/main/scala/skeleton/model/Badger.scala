package skeleton.model

import java.time.LocalDate

import io.circe.generic.JsonCodec

@JsonCodec
final case class Badger(name: String, dob: Option[LocalDate], foods: List[Food])
