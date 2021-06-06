package ar.edu.unahur.obj2.semillasAlViento
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class SemillasTest: DescribeSpec({
    describe("Cualidades de plantas") {
        val menta = Menta(2020, 0.3F)
        val soja = Soja(2021, 0.7F, false)
        val sojaAlta = Soja(2019, 1.5F, false)
        val sojaTrans = Soja(2021, 0.8F, true)

        it("Cualidades de la menta") {
            menta.horasDeSolQueTolera().shouldBe(6)
            menta.esFuerte().shouldBeFalse()
            menta.daSemillas().shouldBeFalse()
        }
        it("Cualidades de la soja") {
            soja.horasDeSolQueTolera().shouldBe(7)
            soja.esFuerte().shouldBeFalse()
            soja.daSemillas().shouldBeFalse()
        }
        it("Cualidades de la soja más alta") {
            sojaAlta.horasDeSolQueTolera().shouldBe(9)
            sojaAlta.esFuerte().shouldBeFalse()
            sojaAlta.daSemillas().shouldBeTrue()
        }
        it("Cualidades de la soja Transgénica") {
            sojaTrans.horasDeSolQueTolera().shouldBe(14)
            sojaTrans.esFuerte().shouldBeTrue()
            sojaTrans.daSemillas().shouldBeFalse()
        }
    }
})