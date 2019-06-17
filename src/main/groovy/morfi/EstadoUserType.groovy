package morfi

import org.hibernate.type.*
import org.hibernate.engine.*
import org.hibernate.engine.spi.*
import org.hibernate.usertype.*

import java.sql.*

class EstadoUserType
//implements UserType
 {
/*
  java.lang.Class returnedClass() {
    Reserva.Estado
  }

  static final STRING_TYPE = StringType.INSTANCE

  int[] sqlTypes() {
    [STRING_TYPE.sqlType()] as int[]
  }

  void nullSafeSet(java.sql.PreparedStatement ps, java.lang.Object o, int index, org.hibernate.engine.spi.SessionImplementor si) {
    if (o.class == Estado.Pendiente) ps.setString('pendiente')
    else if (o.class == Estado.Concretado) ps.setString('concretado')
    else if (o.class == Estado.Anulada) ps.setString('anulada')
  }

  java.lang.Object nullSafeGet(java.sql.ResultSet rs, java.lang.String[] names, org.hibernate.engine.spi.SessionImplementor si, java.lang.Object o) {
    def valor
    if (valor == 'pendiente') new Estado.Pendiente()
    else if (valor == 'concretado') new Estado.Concretado()
    else if (valor == 'anulada') new Estado.Anulada()
  }

	final boolean isMutable() {
		false
	}

	final boolean equals(Object x, Object y) {
    x.class == y.class
	}

	final int hashCode(Object object) {
		object.hashCode()
	}

	final Object deepCopy(Object object) {
		object
	}

	final Object replace(Object a, Object b, Object d) {
		throw new UnsupportedOperationException()
	}

	final Serializable disassemble(Object a) {
		throw new UnsupportedOperationException()
	}

	final Object assemble(Serializable a, Object c) {
		throw new UnsupportedOperationException()
	}
  */
}
