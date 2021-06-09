package ar.edu.unahur.obj2.semillasAlViento
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe

class SemillasTest: DescribeSpec({
    val menta = Menta(2020, 0.3F)
    val soja = Soja(2021, 0.7F)
    val sojaAlta = Soja(2019, 1.5F)
    val sojaTrans = SojaTransgenica(2021, 0.8F)

    val parcelaChica = Parcela(100,75,9)
    val parcelaGrande = Parcela(200,150,8)

    parcelaChica.plantar(sojaAlta)
    parcelaChica.plantar(sojaTrans)

    parcelaGrande.plantar(menta)
    parcelaGrande.plantar(soja)
    parcelaGrande.plantar(sojaAlta)
    parcelaGrande.plantar(sojaTrans)

    describe("Cualidades de plantas") {


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

    describe ("cualidades de las parcelas"){

        it ("cualidades de una parcela chica"){
            parcelaChica.plantas.shouldContainExactly(sojaAlta,sojaTrans)
            parcelaChica.superficie() shouldBe (7500)
            parcelaChica.cantidadMaximaPlantas() shouldBe (1500)

            //Se agrega el test del método tieneComplicaciones(), ahora agregado a la Clase Parcela.
            parcelaChica.tieneComplicaciones().shouldBeFalse()

            shouldThrowAny {  parcelaChica.plantar(menta)}

        }

        it ("cualidades de una parcela grande"){
            parcelaGrande.superficie() shouldBe (30000)
            parcelaGrande.cantidadMaximaPlantas() shouldBe (6000)
            parcelaGrande.plantas.shouldContainExactly(menta,soja,sojaAlta,sojaTrans)
        }

    }

    describe ("cualidades de las agricultoras"){
        val noelia = Agricultora(mutableListOf(parcelaChica,parcelaGrande))

        noelia.parcelasSemilleras().shouldBeEmpty()
        noelia.plantarEstrategicamente(menta)

        parcelaGrande.plantas.shouldContainExactly(menta,soja,sojaAlta,sojaTrans,menta)

    }
})