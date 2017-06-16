package com.freela.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.freela.model.Empresa;
import com.freela.model.Freelancer;
import com.freela.model.Oportunidade;
import com.freela.model.Papel;
import com.freela.model.Usuario;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateus Gabriel on 15/06/2017.
 */

public class FreelaDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "freelaDB.db";

    // Tabelas
    private static final String TABLE_USUARIO = "usuario";
    private static final String TABLE_FREELANCER = "freelancer";
    private static final String TABLE_OPORTUNIDADE = "oportunidade";
    private static final String TABLE_EMPRESA = "empresa";
    private static final String TABLE_FREELANCER_OPORTUNIDADE = "freelancer_oportunidade";
    private static final String TABLE_EMPRESA_OPORTUNIDADE = "empresa_oportunidade";

    // Colunas tabela Usuário
    private static final String COLUMN_NOME = "nome";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_SENHA = "senha";
    private static final String COLUMN_PAPEL = "papel";

    // Colunas em comuns
    private static final String COLUMN_ID = " _id";
    private static final String COLUMN_OPORTUNIDADE_ID = "oportunidade_id";
    private static final String COLUMN_OPT_ID = "opt_id";

    // Colunas tabelas Empresa
    //private static final String COLUMN_DESCRICAO_EMPRESA = "descricao";

    //Colunas tabelas Freelancer
    private static final String COLUMN_PROFISSAO = "profissao";
    private static final String COLUMN_IMAGE_RESOURCE_ID = "ImageResourceId";
    private static final String COLUMN_IS_FAV = "isFav";
    private static final String COLUMN_IS_TURNED = "isTurned";

    // Colunas tabela Oportunidade
    private static final String COLUMN_TITULO = "titulo";
    private static final String COLUMN_DESCRICAO = "descricao";
    private static final String COLUMN_DTINICIO = "dtInicio";
    private static final String COLUMN_DTFIM = "dtFim";
    private static final String COLUMN_IMAGE_RESOURCE_ID_OPT = "ImageResourceId";
    private static final String COLUMN_IS_FAV_OPT = "isFav";
    private static final String COLUMN_IS_TURNED_OPT = "isTurned";

    //Colunas foreign key
    private static  final String COLUMN_USUARIO_ID = "usuario_id";

    // Colunas tabela Freelancer_Oportunidade
    private static final String COLUMN_FREELANCER_ID = "freelancer_id";

    // Colunas tabelas Empresa_Oportunidade
    private static final String COLUMN_EMPRESA_ID = "empresa_id";

    // Criando tabela Usuário
    private static final String CREATE_USUARIO_TABLE = "CREATE TABLE " + TABLE_USUARIO + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NOME + " TEXT, " +
            COLUMN_EMAIL + " TEXT, " +
            COLUMN_SENHA + " TEXT, " +
            COLUMN_PAPEL + " TEXT " + ")";

    // Criando tabela Freelancer
    private static final String CREATE_FREELANCER_TABLE = "CREATE TABLE " + TABLE_FREELANCER + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_PROFISSAO + " TEXT, " +
            COLUMN_IMAGE_RESOURCE_ID + " INTEGER, " +
            COLUMN_IS_FAV + " INTEGER, " +
            COLUMN_IS_TURNED + "INTEGER, " +
            COLUMN_USUARIO_ID + "INTEGER " +
//            "FOREIGN KEY("+ COLUMN_USUARIO_ID +") REFERENCES " + TABLE_USUARIO + "("+ COLUMN_ID +") " +
            ")";

    // Criando tabela Empresa
    private static final String CREATE_EMPRESA_TABLE = "CREATE TABLE " + TABLE_EMPRESA + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USUARIO_ID + "INTEGER " +
//            "FOREIGN KEY("+ COLUMN_USUARIO_ID +") REFERENCES " + TABLE_USUARIO + "("+ COLUMN_ID +") " +
             ")";

    private static final String CREATE_OPORTUNIDADE_TABLE = "CREATE TABLE " + TABLE_OPORTUNIDADE + " (" +
            COLUMN_OPT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TITULO + " TEXT, " +
            COLUMN_DESCRICAO + " TEXT, " +
            COLUMN_DTINICIO + " DATE, " +
            COLUMN_DTFIM + " DATE, " +
            COLUMN_IMAGE_RESOURCE_ID_OPT + " INTEGER, " +
            COLUMN_IS_FAV_OPT + " INTEGER, " +
            COLUMN_IS_TURNED_OPT + " INTEGER " +
            ")";

    private static final String CREATE_FREELAANCER_OPORTUNIDADE = "CREATE TABLE " + TABLE_FREELANCER_OPORTUNIDADE + " (" +
            COLUMN_OPT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_OPORTUNIDADE_ID + " INTEGER, " +
            COLUMN_FREELANCER_ID + " INTEGER " +
//            "FOREIGN KEY("+ COLUMN_OPORTUNIDADE_ID +") REFERENCES " + TABLE_OPORTUNIDADE + "("+ COLUMN_OPT_ID +"), " +
//            "FOREIGN KEY("+ COLUMN_FREELANCER_ID +") REFERENCES " + TABLE_FREELANCER + "("+ COLUMN_ID +") " +
            ")";

    private static final String CREATE_EMPRESA_OPORTUNIDADE = "CREATE TABLE " + TABLE_EMPRESA_OPORTUNIDADE + " (" +
            COLUMN_OPT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_OPORTUNIDADE_ID + " INTEGER, " +
            COLUMN_EMPRESA_ID + " INTEGER " +
//            "FOREIGN KEY("+ COLUMN_OPORTUNIDADE_ID +") REFERENCES " + TABLE_OPORTUNIDADE + "("+ COLUMN_OPT_ID +"), " +
//            "FOREIGN KEY("+ COLUMN_EMPRESA_ID +") REFERENCES " + TABLE_EMPRESA + "("+ COLUMN_ID +") " +
            ")";

    public FreelaDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USUARIO_TABLE);
        db.execSQL(CREATE_FREELANCER_TABLE);
        db.execSQL(CREATE_EMPRESA_TABLE);
        db.execSQL(CREATE_OPORTUNIDADE_TABLE);
        db.execSQL(CREATE_FREELAANCER_OPORTUNIDADE);
        db.execSQL(CREATE_EMPRESA_OPORTUNIDADE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_USUARIO_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_FREELANCER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_EMPRESA_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_OPORTUNIDADE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_FREELAANCER_OPORTUNIDADE);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_EMPRESA_OPORTUNIDADE);
        onCreate(db);
    }

    public long addUsuario(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, usuario.getNome());
        values.put(COLUMN_EMAIL, usuario.getEmail());
        values.put(COLUMN_SENHA, usuario.getSenha());

        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.insert(TABLE_USUARIO, null, values);

        return id;
    }

    public Usuario findUsuario(String login, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where =  COLUMN_EMAIL + " = '" + login + "' AND " + COLUMN_SENHA + " = '" + pass  + "' ";

        String[] columns = new String[]{COLUMN_ID, COLUMN_NOME, COLUMN_EMAIL, COLUMN_SENHA, COLUMN_PAPEL};
        Cursor cursor = db.query(true, TABLE_USUARIO, columns,where, null, null, null, null, null);

        Usuario usuario = new Usuario();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            usuario.setId(Integer.parseInt(cursor.getString(0)));
            usuario.setNome(cursor.getString(1));
            usuario.setEmail(cursor.getString(2));
            usuario.setSenha(cursor.getString(3));
            usuario.setPapel(Papel.valueOf(cursor.getString(4)));
            cursor.close();
        } else {
            usuario = null;
        }

        db.close();
        return usuario;
    }

    public long addFreelancer(Freelancer freelancer) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PROFISSAO, freelancer.getProfissao());
        values.put(COLUMN_IMAGE_RESOURCE_ID, freelancer.getImageResourceId());
        values.put(COLUMN_IS_FAV, freelancer.getIsFav());
        values.put(COLUMN_IS_TURNED, freelancer.getIsTurned());
        values.put(COLUMN_USUARIO_ID, freelancer.getId());

        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.insert(TABLE_FREELANCER, null, values);

        return id;
    }

    public Freelancer findFreelancer(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] columns = new String[]{COLUMN_ID, COLUMN_PROFISSAO, COLUMN_IMAGE_RESOURCE_ID, COLUMN_IS_FAV, COLUMN_IS_TURNED};
        Cursor cursor = db.query(true, TABLE_FREELANCER, columns, COLUMN_USUARIO_ID + "='" + id + "'", null, null, null, null, null);

        Freelancer freelancer = new Freelancer();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            freelancer.setId(Integer.parseInt(cursor.getString(0)));
            freelancer.setProfissao(cursor.getString(1));
            freelancer.setImageResourceId(Integer.parseInt(cursor.getString(2)));
            freelancer.setIsFav(Integer.parseInt(cursor.getString(3)));
            freelancer.setIsTurned(Integer.parseInt(cursor.getString(3)));
            cursor.close();
        } else {
            freelancer = null;
        }

        db.close();
        return freelancer;
    }



    public ArrayList<Freelancer> findFreelancers(ArrayList<Integer> freelancers_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String inClause = freelancers_id.toString();

        inClause = inClause.replace("[","(");
        inClause = inClause.replace("]",")");



        String[] columns = new String[]{COLUMN_ID, COLUMN_PROFISSAO, COLUMN_IMAGE_RESOURCE_ID, COLUMN_IS_FAV, COLUMN_IS_TURNED};
        Cursor cursor = db.query(true, TABLE_FREELANCER, columns, COLUMN_ID + " IN " + inClause + " ", null, null, null, null, null);

        Freelancer freelancer = new Freelancer();
        ArrayList<Freelancer> lstFreelancers = new ArrayList<Freelancer>();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            freelancer.setId(Integer.parseInt(cursor.getString(0)));
            freelancer.setProfissao(cursor.getString(1));
            freelancer.setImageResourceId(Integer.parseInt(cursor.getString(2)));
            freelancer.setIsFav(Integer.parseInt(cursor.getString(3)));
            freelancer.setIsTurned(Integer.parseInt(cursor.getString(3)));
            lstFreelancers.add(freelancer);
            cursor.close();
        } else {
            freelancer = null;
        }

        db.close();
        return lstFreelancers;
    }

    public ArrayList<Freelancer> findFreelancers() {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] columns = new String[]{COLUMN_ID, COLUMN_PROFISSAO, COLUMN_IMAGE_RESOURCE_ID, COLUMN_IS_FAV, COLUMN_IS_TURNED};
        Cursor cursor = db.query(true, TABLE_FREELANCER, columns, null, null, null, null, null, null);

        Freelancer freelancer = new Freelancer();
        ArrayList<Freelancer> lstFreelancers = new ArrayList<Freelancer>();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            freelancer.setId(Integer.parseInt(cursor.getString(0)));
            freelancer.setProfissao(cursor.getString(1));
            freelancer.setImageResourceId(Integer.parseInt(cursor.getString(2)));
            freelancer.setIsFav(Integer.parseInt(cursor.getString(3)));
            freelancer.setIsTurned(Integer.parseInt(cursor.getString(3)));
            lstFreelancers.add(freelancer);
            cursor.close();
        } else {
            freelancer = null;
        }

        db.close();
        return lstFreelancers;
    }

    public long addEmpresa(Empresa empresa) {
        ContentValues values = new ContentValues();
        //values.put(COLUMN_NOME, empresa.());
        values.put(COLUMN_USUARIO_ID, empresa.getId());

        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.insert(TABLE_EMPRESA, null, values);

        return id;
    }

    public long addOportunidade(Oportunidade oportunidade, int empresa_id) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITULO, oportunidade.getTitulo());
        values.put(COLUMN_DESCRICAO, oportunidade.getDescricao());
        values.put(COLUMN_DTINICIO, sdf.format(oportunidade.getDtInicio()));
        values.put(COLUMN_DTFIM, sdf.format(oportunidade.getDtFim()));
        values.put(COLUMN_IMAGE_RESOURCE_ID_OPT, oportunidade.getImageResourceId());
        values.put(COLUMN_IS_FAV_OPT, oportunidade.getIsFav());
        values.put(COLUMN_IS_TURNED_OPT, oportunidade.getIsTurned());

        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.insert(TABLE_OPORTUNIDADE, null, values);

        addEmpresaOportunidade(id, empresa_id);

        return id;
    }

    public ArrayList<Oportunidade> findOportunidades(List<Integer> ids) {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] columns = new String[]{COLUMN_ID, COLUMN_TITULO, COLUMN_DESCRICAO, COLUMN_DTFIM, COLUMN_DTINICIO, COLUMN_IMAGE_RESOURCE_ID_OPT, COLUMN_IS_FAV_OPT, COLUMN_IS_TURNED_OPT};
        Cursor cursor = db.query(true, TABLE_OPORTUNIDADE, columns, COLUMN_OPT_ID + " IN (" + ids + ")", null, null, null, null, null);

        Oportunidade oportunidade = new Oportunidade();
        ArrayList<Oportunidade> oportunidades = new ArrayList<>();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            oportunidade.setId(Integer.parseInt(cursor.getString(0)));
            oportunidade.setTitulo(cursor.getString(1));
            oportunidade.setDescricao(cursor.getString(2));
            oportunidade.setDtFim(Date.valueOf(cursor.getString(3)));
            oportunidade.setDtInicio(Date.valueOf(cursor.getString(4)));
            oportunidade.setImageResourceId(Integer.parseInt(cursor.getString(5)));
            oportunidade.setIsFav(Integer.parseInt(cursor.getString(6)));
            oportunidade.setIsTurned(Integer.parseInt(cursor.getString(7)));
            oportunidades.add(oportunidade);
            cursor.close();
        } else {
            oportunidade = null;
        }

        db.close();
        return oportunidades;
    }

    public ArrayList<Oportunidade> findOportunidades() {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] columns = new String[]{COLUMN_OPT_ID, COLUMN_TITULO, COLUMN_DESCRICAO, COLUMN_DTFIM, COLUMN_DTINICIO, COLUMN_IMAGE_RESOURCE_ID_OPT, COLUMN_IS_FAV_OPT, COLUMN_IS_TURNED_OPT};
        Cursor cursor = db.query(true, TABLE_OPORTUNIDADE, columns, null, null, null, null, null, null);

        Oportunidade oportunidade = new Oportunidade();
        ArrayList<Oportunidade> oportunidades = new ArrayList<Oportunidade>();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            oportunidade.setId(Integer.parseInt(cursor.getString(0)));
            oportunidade.setTitulo(cursor.getString(1));
            oportunidade.setDescricao(cursor.getString(2));
            oportunidade.setDtFim(Date.valueOf(cursor.getString(3)));
            oportunidade.setDtInicio(Date.valueOf(cursor.getString(4)));
            oportunidade.setImageResourceId(Integer.parseInt(cursor.getString(5)));
            oportunidade.setIsFav(Integer.parseInt(cursor.getString(6)));
            oportunidade.setIsTurned(Integer.parseInt(cursor.getString(7)));
            oportunidades.add(oportunidade);
            cursor.close();
        } else {
            oportunidade = null;
        }

        db.close();
        return oportunidades;
    }

    public long addFreelancerOportunidade(int oportunidade_id, int freelancer_id) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_OPORTUNIDADE_ID, oportunidade_id);
        values.put(COLUMN_FREELANCER_ID, freelancer_id);

        SQLiteDatabase db = this.getWritableDatabase();
        long id_ = db.insert(TABLE_FREELANCER_OPORTUNIDADE, null, values);

        return id_;
    }


    public ArrayList<Oportunidade> findFreelancerOportunidade(int freelancer_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] columns = new String[]{COLUMN_OPORTUNIDADE_ID};
        Cursor cursor = db.query(true, TABLE_FREELANCER_OPORTUNIDADE, columns, COLUMN_FREELANCER_ID + "='" + freelancer_id + "'", null, null, null, null, null);

        ArrayList<Integer> oportunidades_id = new ArrayList<>();
        ArrayList<Oportunidade> oportunidades = new ArrayList<>();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            oportunidades_id.add(Integer.parseInt(cursor.getString(0)));
            cursor.close();
        }

        if(!oportunidades_id.isEmpty()) {
            oportunidades = findOportunidades(oportunidades_id);
        }

        db.close();
        return oportunidades;
    }

    public long addEmpresaOportunidade(long oportunidade_id, int empresa_id) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_OPORTUNIDADE_ID, oportunidade_id);
        values.put(COLUMN_EMPRESA_ID, empresa_id);

        SQLiteDatabase db = this.getWritableDatabase();
        long id_ = db.insert(TABLE_EMPRESA_OPORTUNIDADE, null, values);

        return id_;
    }

    public ArrayList<Oportunidade> findEmpresaOportunidade(int empresa_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] columns = new String[]{COLUMN_OPORTUNIDADE_ID};
        Cursor cursor = db.query(true, TABLE_FREELANCER_OPORTUNIDADE, columns, COLUMN_EMPRESA_ID + "='" + empresa_id + "'", null, null, null, null, null);

        ArrayList<Integer> oportunidades_id = new ArrayList<>();
        ArrayList<Oportunidade> oportunidades = new ArrayList<>();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            oportunidades_id.add(Integer.parseInt(cursor.getString(0)));
            cursor.close();
        }

        if(!oportunidades_id.isEmpty()) {
            oportunidades = findOportunidades(oportunidades_id);
        }

        db.close();
        return oportunidades;
    }

    public boolean deleteFreelancerOportunidade(int oportunidade_id, int freelancer_id){
            SQLiteDatabase db = this.getWritableDatabase();
            return db.delete(TABLE_EMPRESA_OPORTUNIDADE, COLUMN_OPORTUNIDADE_ID + "=" + oportunidade_id + " AND " + COLUMN_FREELANCER_ID + " = " + freelancer_id, null) > 0;
    }


}
