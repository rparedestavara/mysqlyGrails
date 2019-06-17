
<g:form action="reservar">

  <p>
    fecha: <input type="date" name="fecha" />
  </p>

  <p>
    <g:select
      name="restaurante"
      from="${restaurantes}"
      optionKey="id"
      optionValue="nombre"
      noSelection="['': '(elegí una opción)']"
      />
  </p>

  <p>
    <g:select
      name="comensal"
      from="${comensales}"
      optionKey="id"
      optionValue="nombreDeUsuario"
      noSelection="['': '(elegí una opción)']"
      />
  </p>

  <p>
    <input type="submit" />
  </p>


</g:form>
