package com.example.caloriescalculator.room_db;

import android.content.Context;
import android.os.AsyncTask;

import com.example.caloriescalculator.db.food;

import java.util.List;

public class FoodRepository {

    private Context mContext;


    public FoodRepository(Context mContext) {
        this.mContext = mContext;
    }

    public void getFood(Callback callback){
        GetTask getTask = new GetTask(mContext,callback);
        getTask.execute();

    }
    public void insertFood(food item, InsertCallback callback){
        InsertTask insrInsertTask = new InsertTask(mContext,callback);
        insrInsertTask.execute(item);
    }

    public void deleteFood(deleteCallback callback){
        DeleteTask deleteTask = new DeleteTask(mContext,callback);
        deleteTask.execute();

    }
    private static class GetTask extends AsyncTask<Void , Void , List<food>>{

        private Context mContext;
        private Callback mCallback;

        public GetTask(Context context, Callback callback){
            this.mContext = context;
            this.mCallback = callback;
        }

        @Override
        protected List<food> doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getInstance(mContext);
            List<food> itemList = db.foodDao().getAll();
            return itemList;
        }

        @Override
        protected void onPostExecute(List<food> ledgerItems) {

            super.onPostExecute(ledgerItems);
            mCallback.onGetFood(ledgerItems);
        }
    }

    public interface Callback {
        void onGetFood(List<food> itemList);
    }

    private static class InsertTask extends AsyncTask<food,Void,Void > {

        private Context mContext;
        private InsertCallback mCallback;
        public InsertTask(Context context, InsertCallback callback){
            this.mContext = context;
            this.mCallback=callback;
        }



        @Override
        protected Void doInBackground(food... ledgerItems) {
            AppDatabase db = AppDatabase.getInstance(mContext);
            db.foodDao().insert(ledgerItems[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mCallback.onInsertSuccess();

        }
    }

    public interface InsertCallback{
        void onInsertSuccess();
    }





    private static class DeleteTask extends AsyncTask<Void,Void,Void > {

        private Context mContext;
        private deleteCallback mCallback;
        public DeleteTask(Context context, deleteCallback callback){
            this.mContext = context;
            this.mCallback= callback;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mCallback.deleteFood();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getInstance(mContext);
            db.foodDao().deleteAll();
            return null;
        }
    }

    public interface deleteCallback {
        void deleteFood();
    }


}
