package ar.edu.unahur.obj2.semillasAlViento

//cambiamos var por val
abstract class Planta(val anioObtencionSemilla: Int, val altura: Float) {

  fun esFuerte() = this.horasDeSolQueTolera() > 10

  // Se pasa el método tiene complicaciones a la clase Parcela

  abstract fun horasDeSolQueTolera(): Int

  //this.esFuerte()) pasa a la funcion abstracta
  open fun daSemillas(): Boolean = this.esFuerte()
}

class Menta(anioObtencionSemilla: Int, altura: Float) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera() = 6
  override fun daSemillas() = super.daSemillas() || altura > 0.4
}
//representamos soja y soja transgénica por separado
open class Soja(anioObtencionSemilla: Int, altura: Float) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera(): Int  {
    return when {
      altura < 0.5  -> 6
      altura < 1    -> 7
      else          -> 9
    }
  }

  override fun daSemillas(): Boolean  {
    return super.daSemillas() || (this.anioObtencionSemilla > 2007 && this.altura > 1)
  }
}

class SojaTransgenica(anioObtencionSemilla: Int, altura: Float) : Soja(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera(): Int {
    return super.horasDeSolQueTolera() * 2
  }

  override fun daSemillas(): Boolean {
    return false
  }
}

