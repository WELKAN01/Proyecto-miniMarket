package app.market.proyectominimarket.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import app.market.proyectominimarket.model.car;
import app.market.proyectominimarket.model.producto;
import app.market.proyectominimarket.model.usuario;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DBuser extends SQLiteOpenHelper {
    private static String DATABASE="MARKET.bd";
    private static String TABLANAME="usuario";

    private static String CREATE="CREATE TABLE "+TABLANAME+"("+
            "ID INTEGER primary key autoincrement, "+
            "NOMBRE TEXT ,"+
            "NACIMIENTO DATE ,"+
            "CORREO TEXT ,"+
            "PASSWORD TEXT"+
            ")";
    private static String DROP="DROP TABLE IF EXISTS "+TABLANAME;

    private tablaproducto Tablaproducto=new tablaproducto();
    private tablausuario Tablausuario=new tablausuario();
    private tablacarrito Tablacarrito=new tablacarrito();
    public DBuser(@Nullable Context context) {
        super(context, DATABASE, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    //Creacion de las tablas usuario y productos de la base de datos
    //se va establecer datos de producto de forma predeterminado al programa
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Tablausuario.creartablausuario());
        db.execSQL(Tablaproducto.creartablaproducto());
        db.execSQL(Tablacarrito.crearCarrito());
        usuariopredeterminado(db);
        insertarproductos(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL(Tablausuario.borrartablausuario());
            db.execSQL(Tablaproducto.Eliminartablaproducto());
            db.execSQL(Tablacarrito.eliminarCarrito());
            onCreate(db);
    }

    //registro de cuenta de una persona
    public Boolean insertarDatos(String nombre, String Correo, String Date, String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("NOMBRE",nombre);
        contentValues.put("NACIMIENTO",Date);
        contentValues.put("CORREO",Correo);
        contentValues.put("PASSWORD",password);
        Long result=db.insert(Tablausuario.getTABLANAME()+"",null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    //verificar si usuario existe en la parte de perfil

    public usuario verificarusuario(String Correo){

        SQLiteDatabase db=this.getWritableDatabase();
        usuario user = null;
        Cursor cursor=db.rawQuery("SELECT NOMBRE,CORREO,NACIMIENTO FROM "+Tablausuario.getTABLANAME()+" WHERE CORREO=? ",new String[]{Correo});
        if (cursor.moveToFirst()){
            String nombre=cursor.getString(cursor.getColumnIndexOrThrow("NOMBRE"));
            String correo=cursor.getString(cursor.getColumnIndexOrThrow("CORREO"));
            String nacer=cursor.getString(cursor.getColumnIndexOrThrow("NACIMIENTO"));
            user=new usuario(nombre,correo,nacer);
        }
        cursor.close();
        return user;
    }



    //verificar si usuario y contraseña existe
    public boolean verificarusuarioyContra(String correo,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+Tablausuario.getTABLANAME()+" WHERE CORREO=? and PASSWORD=?",
                new String[]{correo,password});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    //usuario predeterminado
    public void usuariopredeterminado(SQLiteDatabase db){
        ContentValues user=new ContentValues();
        user.put("NOMBRE","Prueba1");
        user.put("NACIMIENTO","07-30-2007");
        user.put("CORREO","Googleprueba@gmail.com");
        user.put("PASSWORD","123456789");
        db.insert(Tablausuario.getTABLANAME(),null,user);
    }

    //funcion de insertacion de productos
    public void insertarproductos(SQLiteDatabase db){
        List<ContentValues> listproducto=new ArrayList<>();
        ContentValues cv1=new ContentValues();
        cv1.put("NOMBRE","coca cola 550ml");
        cv1.put("PRECIO",3.50);
        cv1.put("STOCK",15);
        cv1.put("CATEGORIA","bebidas");
        listproducto.add(cv1);
        ContentValues cv2=new ContentValues();
        cv2.put("NOMBRE","inka cola 550ml");
        cv2.put("PRECIO",3.50);
        cv2.put("STOCK",15);
        cv2.put("CATEGORIA","bebidas");
        listproducto.add(cv2);
        ContentValues cv3=new ContentValues();
        cv3.put("NOMBRE","zanahoria 1u");
        cv3.put("PRECIO",0.80);
        cv3.put("STOCK",25);
        cv3.put("CATEGORIA","vegetales");
        listproducto.add(cv3);
        ContentValues cv4=new ContentValues();
        cv4.put("NOMBRE","choclo x kg");
        cv4.put("PRECIO",4.00);
        cv4.put("STOCK",15);
        cv4.put("CATEGORIA","vegetales");
        listproducto.add(cv4);
        ContentValues cv5=new ContentValues();
        cv5.put("NOMBRE","manzana x unidad");
        cv5.put("PRECIO",1.00);
        cv5.put("STOCK",15);
        cv5.put("CATEGORIA","frutas");
        listproducto.add(cv5);
        ContentValues cv6=new ContentValues();
        cv6.put("NOMBRE","platano x kilo");
        cv6.put("PRECIO",6.00);
        cv6.put("STOCK",15);
        cv6.put("CATEGORIA","frutas");
        listproducto.add(cv6);
        ContentValues cv7=new ContentValues();
        cv7.put("NOMBRE","pan bimbo 25unidades");
        cv7.put("PRECIO",5.00);
        cv7.put("STOCK",10);
        cv7.put("CATEGORIA","almacen");
        listproducto.add(cv7);
        ContentValues cv8=new ContentValues();
        cv8.put("NOMBRE","aceite 1L");
        cv8.put("PRECIO",7.00);
        cv8.put("STOCK",10);
        cv8.put("CATEGORIA","almacen");
        listproducto.add(cv8);
        ContentValues cv9=new ContentValues();
        cv9.put("NOMBRE","gillete 2 hojas");
        cv9.put("PRECIO",1.00);
        cv9.put("STOCK",25);
        cv9.put("CATEGORIA","domesticos");
        listproducto.add(cv9);
        ContentValues cv10=new ContentValues();
        cv10.put("NOMBRE","fosforo caja pequeña");
        cv10.put("PRECIO",7.00);
        cv10.put("STOCK",10);
        cv10.put("CATEGORIA","domesticos");
        listproducto.add(cv10);
        for (ContentValues values:listproducto){
            db.insert(Tablaproducto.getTABLANAME(),null,values);
        }
    }

    //funcion para obtener los productos mediante busqueda, en ello ordernarlo y devolverlos en datos
    //con estructura List mediante la clase producto
    public List<producto> obtenerproducto(String categoria){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        List<producto> prod=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT NOMBRE,PRECIO,STOCK,CATEGORIA FROM "+
                        Tablaproducto.getTABLANAME()+
                        " WHERE CATEGORIA=?"
                ,new String[]{categoria});
        while (cursor.moveToNext()){
            String nombre=cursor.getString(cursor.getColumnIndexOrThrow("NOMBRE"));
            Double precio=cursor.getDouble(cursor.getColumnIndexOrThrow("PRECIO"));
            int stock=cursor.getInt(cursor.getColumnIndexOrThrow("STOCK"));
            String Categoria=cursor.getString(cursor.getColumnIndexOrThrow("CATEGORIA"));
            prod.add(new producto(nombre,precio,stock,Categoria));
        }

        return prod;
    }

    public List<producto> obtenerproductototal(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        List<producto> prod=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT NOMBRE,PRECIO,STOCK,CATEGORIA FROM "+
                        Tablaproducto.getTABLANAME()
                ,null);
        while (cursor.moveToNext()){
            String nombre=cursor.getString(cursor.getColumnIndexOrThrow("NOMBRE"));
            Double precio=cursor.getDouble(cursor.getColumnIndexOrThrow("PRECIO"));
            int stock=cursor.getInt(cursor.getColumnIndexOrThrow("STOCK"));
            String Categoria=cursor.getString(cursor.getColumnIndexOrThrow("CATEGORIA"));
            prod.add(new producto(nombre,precio,stock,Categoria));
        }

        return prod;
    }


    //obtener los productos del carrito mediante la cuenta ingresada
    public Cursor obtenerproductosdelcarrito(String nom){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM "+Tablacarrito.getTABLA_NAME()+" WHERE Correo_usuario LIKE ?",
                new String[]{nom});
        return  res;
    }

    public Cursor obtenerproductosdelcarritoanalisis(String nom,String prod){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM "+Tablacarrito.getTABLA_NAME()+
                        " WHERE Correo_usuario LIKE ? AND nombre LIKE ?",
                new String[]{nom,prod});

        return  res;
    }
    //insertar los datos a la bd temporal, en ello si no existe lo insertamos.
    //en caso no se va actualizar a la cantidad mas que se le agrega.
    public boolean insertarCarrito(car carrito,String nombre) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", carrito.getNombre());
        contentValues.put("cantidad", carrito.getCantidad());
        contentValues.put("totalpagar", carrito.getTotalpago());
        contentValues.put("Correo_usuario", nombre);
        Cursor aux = obtenerproductosdelcarritoanalisis(nombre,carrito.getNombre());
        int res = 0;
        if (aux!=null && aux.getCount() > 0) {
            while (aux.moveToNext()) {
                if (aux.getString(1).equalsIgnoreCase(carrito.getNombre())) {
                    int cantidad_actual=aux.getInt(2)+carrito.getCantidad();
                    res=actualizarcarrito(carrito.getNombre(), cantidad_actual, carrito.getPrecio());
                    break;
                }
            }
        } else {
            res = (int) db.insert(Tablacarrito.getTABLA_NAME(), null, contentValues);
        }
        return res == -1 ? false : true;
    }

    //funcion para actualizar carrito
    private int actualizarcarrito(String nombreprod,int cantidadActual,double precio) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        DecimalFormat format=new DecimalFormat("#.##");
        precio=Double.parseDouble(format.format(precio));
        cv.put("cantidad",cantidadActual);
        cv.put("totalpagar",cantidadActual*precio);
        int res=db.update(Tablacarrito.getTABLA_NAME(),cv,"nombre = ?",
                new String[]{nombreprod});
        return res;
    }

    public boolean eliminarcarrito(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        int res=db.delete(Tablacarrito.getTABLA_NAME(),"idcarro = ?",new String[]{id});
        return res==-1?false:true;
    }

    public boolean deleteCar(String correo) {
        SQLiteDatabase db=this.getWritableDatabase();
        int res=db.delete(Tablacarrito.getTABLA_NAME(),"Correo_usuario = ?",new String[]{
                correo
        });
        return  res==-1?false:true;
    }
}
