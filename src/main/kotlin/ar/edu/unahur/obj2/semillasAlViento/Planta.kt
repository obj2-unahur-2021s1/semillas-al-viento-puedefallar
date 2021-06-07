package ar.edu.unahur.obj2.semillasAlViento
//Mutacion controlada : Se genera una variable Var a la cual luego nunca se le cambio el valor.
abstract class Planta(val anioObtencionSemilla: Int, val altura: Float) {

  fun esFuerte() = this.horasDeSolQueTolera() > 10

  // Acoplamiento- Es un metodo que acopla a las clases parcela y planta.
  // Cohesivo - Poco cohesivo ya que realiza metodos que no hacen falta.
  // Se pasa el método tiene complicaciones a la clase Parcela

  abstract fun horasDeSolQueTolera(): Int

  //redundancia minima:  La primera parte (this.esFuerte()) está repetida en todas las subclases.
  open fun daSemillas(): Boolean = this.esFuerte()
}

class Menta(anioObtencionSemilla: Int, altura: Float) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera() = 6
  override fun daSemillas() = super.daSemillas() || altura > 0.4
}
//*cohesion: con una sola clase se está queriendo representar a dos tipos de Soja,
// usando un booleano para diferenciarlas.
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

