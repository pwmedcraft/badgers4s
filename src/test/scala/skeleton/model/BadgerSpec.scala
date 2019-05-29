package skeleton.model

import java.time.LocalDate

import com.stephenn.scalatest.circe.JsonMatchers
import io.circe.CursorOp.DownField
import io.circe.DecodingFailure
import io.circe.parser.decode
import io.circe.syntax._
import org.scalatest.{FlatSpec, Matchers}

class BadgerSpec extends FlatSpec with Matchers with JsonMatchers {

  "Badger decoder" should "decode JSON into a Badger as expected" in {
    decode[Badger]("""{"name" : "Keith", "dob": "2017-07-14", "foods" : ["N", "HF"]}""") should be (Right(Badger("Keith", Some(LocalDate.of(2017,7,14)), List(Nuts, HedgehogsFeet))))
  }

  it should "still work if the JSON doesn't contain the optional dob field" in {
    decode[Badger]("""{"name" : "Keith", "foods" : ["N", "HF"]}""") should be (Right(Badger("Keith", None, List(Nuts, HedgehogsFeet))))
  }

  it should "fail if the JSON doesn't contain name" in {
    decode[Badger]("""{"dob": "2017-07-14", "foods" : ["N", "HF"]}""") should be (Left(DecodingFailure("Attempt to decode value on failed cursor", List(DownField("name")))))
  }

  "Badger encoder" should "encode Badgers into the expected JSON" in {
    List(
      Badger("Keith", Some(LocalDate.of(2017, 7, 14)), List(Nuts, HedgehogsFeet)),
      Badger("Geoffrey", Some(LocalDate.of(2015, 10, 27)), List(Berries, Seeds, HedgehogsFeet)),
      Badger("Barbara", Some(LocalDate.of(2016, 3, 2)), List(HedgehogsFeet))
    ).asJson.toString should matchJson(
      """[
        |{"name" : "Keith", "dob": "2017-07-14", "foods" : ["N", "HF"]},
        |{"name" : "Geoffrey", "dob": "2015-10-27", "foods" : ["B", "S", "HF"]},
        |{"name" : "Barbara", "dob": "2016-03-02", "foods" : ["HF"]}
        |]
      """.stripMargin
    )
  }

}
