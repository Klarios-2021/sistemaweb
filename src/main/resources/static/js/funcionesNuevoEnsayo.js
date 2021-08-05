function fillSelect(){

    /*
    document.getElementById("selectEstablecimiento").hidden =
        document.getElementById("laboratorio").value != "" ? false : true

     */

    //var laboratorioElegido = document.getElementById("laboratorio").value

    /*<![CDATA[*/
    var laboratorios = [[${laboratorios}]]
    /*]]>*/
    console.log(laboratorios.length)
    document.getElementById("asd").innerHTML = "5"
    /*document.getElementById("hasta").value = null;
    document.getElementById("nombrePaciente").value = null;*/
}
