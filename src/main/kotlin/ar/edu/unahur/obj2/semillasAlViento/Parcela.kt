package ar.edu.unahur.obj2.semillasAlViento

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {

  val plantas = mutableListOf<Planta>()
  //Redundancia - Es redundante tener una variable que informe la cantidad de plantas ya que eso lo podemos saber
  // solo con lista.size
  // Se elimina la variable cantidadPlantas por redundancia

  fun superficie() = ancho * largo
  //*Está dejando lógica redundante, la cualidad es Redundancia mínima
  //En vez de calcular ancho * largo, deberia hacer uso del método superficie()
  fun cantidadMaximaPlantas() =
    if (ancho > largo) superficie()/ 5 else superficie() / 3 + largo


  fun plantar(planta: Planta) {
    //*Claro, el problema es que lo imprimen en
    // pantalla en lugar de lanzar un error que corte la ejecución

    // Reemplazamos el string por un error
    if (plantas.size == this.cantidadMaximaPlantas()) {
      throw error ("Ya no hay lugar en esta parcela")
    } else if (horasSolPorDia > planta.horasDeSolQueTolera() + 2) {
      throw error ("No se puede plantar esto acá, se va a quemar")
    } else {
      plantas.add(planta)
    }
  }
  fun tieneComplicaciones() =
    plantas.any { it.horasDeSolQueTolera() < horasSolPorDia }
}
//si la tierra no puede ser vendida ni comprada, val parcelas no deberia ser inmutable?
//*Mutaciones controladas
//por que importan para este modelo los ahorros y el precio de las parcelas?

//*mutaciones controladas: parcelas debería ser inmutable, porque en el enunciado
// dice que no se pueden comprar ni vender.
class Agricultora(val parcelas: List<Parcela>) {
  // Se elimina la variable ahorroEnPesos y se elimina el metodo comprarParcela

  fun parcelasSemilleras() =
    parcelas.filter {
      parcela -> parcela.plantas.all {
        planta -> planta.daSemillas()
      }
    }

  // Poco cohesivo ya que la clase realiza metodos de mas.
//*cohesion: este método hace dos cosas: busca la parcela y luego la planta.
// Se podría delegar la primera en otro método.

  fun buscarLaElegida() : Parcela {
    return parcelas.maxBy { it.cantidadMaximaPlantas() - it.plantas.size }!!
  }

  fun plantarEstrategicamente(planta: Planta) {

    buscarLaElegida().plantas.add(planta)
    //*Bien, o sea que hay problemas de robustez. Además, hay uno de Acoplamiento:
    //La Agricultora ahora queda atada a saber que las Parcelas tienen una lista mutable de
  // plantas, que es un detalle de implementación que no debería conocer.
  }
}
