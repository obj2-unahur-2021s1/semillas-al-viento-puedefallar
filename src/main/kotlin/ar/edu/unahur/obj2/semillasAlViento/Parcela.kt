package ar.edu.unahur.obj2.semillasAlViento

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {

  //Mutabilidad - Se deja abierta la lista para agregar elementos sin validaciones.
  val plantas = mutableListOf<Planta>()
  //Redundancia - Es redunante una variable que informe la cantidad de plantas ya que eso lo podemos saber solo con la lista.
  var cantidadPlantas = 0

  fun superficie() = ancho * largo
  fun cantidadMaximaPlantas() =
    if (ancho > largo) ancho * largo / 5 else ancho * largo / 3 + largo

  fun plantar(planta: Planta) {
    // Robustez - Se informa un error ante un posible problema al agregar elementos a la lista
    // Acoplamiento - De las clases Planta y Parcela.
    if (cantidadPlantas == this.cantidadMaximaPlantas()) {
      println("Ya no hay lugar en esta parcela")
    } else if (horasSolPorDia > planta.horasDeSolQueTolera() + 2) {
      println("No se puede plantar esto acá, se va a quemar")
    } else {
      plantas.add(planta)
      cantidadPlantas += 1
    }
  }
}

class Agricultora(val parcelas: MutableList<Parcela>) {
  var ahorrosEnPesos = 20000

  // Suponemos que una parcela vale 5000 pesos
  // Flexibilidad - Es sencillo agregar elementos a la lista , sin validaciones.
  fun comprarParcela(parcela: Parcela) {
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
  fun plantarEstrategicamente(planta: Planta) {
    val laElegida = parcelas.maxBy { it.cantidadMaximaPlantas() - it.cantidadPlantas }!!
    laElegida.plantas.add(planta)
  }
}
