package app.market.proyectominimarket.Db;


public class tablacarrito {

    private final String TABLA_NAME="carro";

    private final String CREATE="CREATE TABLE "+TABLA_NAME+"("+
            "idcarro INTEGER primary key autoincrement," +
            "nombre TEXT ,"+
            "cantidad INTEGER ," +
            "totalpagar REAL ," +
            "Correo_usuario TEXT"+
            ")";

    private static final String DROP="DROP TABLE IF EXISTS "+ tablausuario.getTABLANAME();

    public String getTABLA_NAME() {
        return TABLA_NAME;
    }

    public String crearCarrito() {
        return CREATE;
    }
    public String eliminarCarrito(){
        return DROP;
    }
}

