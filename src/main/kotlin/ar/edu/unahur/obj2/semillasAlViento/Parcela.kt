package ar.edu.unahur.obj2.semillasAlViento

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {
  //*Esto esta bien
  //Mutabilidad - Se deja abierta la lista para agregar elementos sin validaciones.
  val plantas = mutableListOf<Planta>()
  //Redundancia - Es redundante tener una variable que informe la cantidad de plantas ya que eso lo podemos saber
  // solo con lista.size
  var cantidadPlantas = 0

  fun superficie() = ancho * largo
  //*Está dejando lógica redundante, la cualidad es Redundancia mínima
  //En vez de calcular ancho * largo, deberia hacer uso del método superficie()
  //Abstraccion
  fun cantidadMaximaPlantas() =
    if (ancho > largo) ancho * largo / 5 else ancho * largo / 3 + largo


  fun plantar(planta: Planta) {
    //*Claro, el problema es que lo imprimen en
    // pantalla en lugar de lanzar un error que corte la ejecución

    // Robustez - Se informa un error ante un posible problema al agregar elementos a la lista
    if (cantidadPlantas == this.cantidadMaximaPlantas()) {
      println("Ya no hay lugar en esta parcela")
    } else if (horasSolPorDia > planta.horasDeSolQueTolera() + 2) {
      println("No se puede plantar esto acá, se va a quemar")
    } else {
      plantas.add(planta)
      cantidadPlantas += 1
    }
  }
  fun tieneComplicaciones() =
    plantas.any { it.horasDeSolQueTolera() < horasSolPorDia }
}
//si la tierra no puede ser vendida ni comprada, val parcelas no deberia ser inmutable?
//*Mutaciones controladas
//por que importan para este modelo los ahorros y el precio de las parcelas?
//Error de simplicidad --> YAGNI

//*mutaciones controladas: parcelas debería ser inmutable, porque en el enunciado
// dice que no se pueden comprar ni vender.
class Agricultora(val parcelas: MutableList<Parcela>) {
  var ahorrosEnPesos = 20000

  // Suponemos que una parcela vale 5000 pesos
  // Flexibilidad - Es sencillo agregar elementos a la lista , sin validaciones.
  fun comprarParcela(parcela: Parcela) { //*Simplicidad: no está en el enunciado
    if (ahorrosEnPesos >= 5000) {
      parcelas.add(parcela)
      ahorrosEnPesos -= 5000
    }
  }

  fun parcelasSemilleras() =
    parcelas.filter {
      parcela -> parcela.plantas.all {
        planta -> planta.daSemillas()
      }
    }

  // Poco cohesivo ya que la clase realiza metodos de mas.
//*cohesion: este método hace dos cosas: busca la parcela y luego la planta.
// Se podría delegar la primera en otro método.

  fun plantarEstrategicamente(planta: Planta) {
    val laElegida = parcelas.maxBy { it.cantidadMaximaPlantas() - it.cantidadPlantas }!!
    //para respetar las restricciones propias de cada parcela se debería usar el método plantar(planta), no el add(planta)
    //Mutaciones controladas
    laElegida.plantas.add(planta)
    //*Bien, o sea que hay problemas de robustez. Además, hay uno de Acoplamiento:
    //La Agricultora ahora queda atada a saber que las Parcelas tienen una lista mutable de
  // plantas, que es un detalle de implementación que no debería conocer.
  }
}
