package ar.edu.unahur.obj2.semillasAlViento

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {

  val plantas = mutableListOf<Planta>()

  // Se elimina la variable cantidadPlantas por redundancia

  fun superficie() = ancho * largo

  //En vez de calcular ancho * largo, usa método superficie()
  fun cantidadMaximaPlantas() =
    if (ancho > largo) superficie()/ 5 else superficie() / 3 + largo


  fun plantar(planta: Planta) {
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

class Agricultora(val parcelas: List<Parcela>) {
  // Se elimina la variable ahorroEnPesos y se elimina el metodo comprarParcela
  // val parcela ahora es inmutable

  fun parcelasSemilleras() =
    parcelas.filter {
      parcela -> parcela.plantas.all {
        planta -> planta.daSemillas()
      }
    }

  // Se delega buscar elegida en otro método.
  fun buscarLaElegida() : Parcela {
    return parcelas.maxBy { it.cantidadMaximaPlantas() - it.plantas.size }!!
  }

  fun plantarEstrategicamente(planta: Planta) {
    buscarLaElegida().plantas.add(planta)
  }
}
