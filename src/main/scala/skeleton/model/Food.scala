package skeleton.model

import io.circe.{Decoder, Encoder}

sealed trait Food extends Product with Serializable

case object Berries extends Food

case object Nuts extends Food

case object Seeds extends Food

case object HedgehogsFeet extends Food

object Food {
  implicit val foodEncoder: Encoder[Food] = Encoder.encodeString.contramap[Food] {
    _ match {
      case Berries       => "B"
      case Nuts          => "N"
      case Seeds         => "S"
      case HedgehogsFeet => "HF"
    }
  }
  implicit val foodDecoder: Decoder[Food] = Decoder.decodeString.emap { str =>
    str match {
      case "B" => Right(Berries)
      case "N" => Right(Nuts)
      case "S" => Right(Seeds)
      case "HF" => Right(HedgehogsFeet)
      case _ => Left(s"Food $str not found")
    }
  }
}