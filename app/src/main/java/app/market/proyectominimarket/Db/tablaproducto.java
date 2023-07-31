package app.market.proyectominimarket.Db;

public class tablaproducto {
    private static String TABLANAME="producto";

    public static final String getTABLANAME() {
        return TABLANAME;
    }
    private static final String CREATE="CREATE TABLE "+TABLANAME+"("+
            "ID INTEGER primary key autoincrement, "+
            "NOMBRE TEXT ,"+
            "PRECIO REAL ,"+
            "STOCK INTEGER ,"+
            "CATEGORIA TEXT"+
            ")";
    private static final String DROP="DROP TABLE IF EXISTS "+TABLANAME;

    public String creartablaproducto(){
        return CREATE;
    }
    public String Eliminartablaproducto(){
        return DROP;
    }
}
