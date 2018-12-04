package com.example.wk.parserdbproducts;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wk.parserdbproducts.POJOS.GroupOfFood;
import com.example.wk.parserdbproducts.POJOS.ItemOfGlobalBase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    static RecyclerView recyclerView;
    ItemAdapter itemAdapter;
    private StorageReference mStorageRef;
    ImageView imageView;

    static String url = "http://www.calorizator.ru/product/pix?page=1";
    static int firstElement = 12;
    static int lastElement = 176;
    static String USER_AGENT = "Mozilla";
    static String TAG_DIV = "</div>";
    /*static String TAG_OF_KCAL = "field field-type-number-decimal field-field-kcal";
    static String TAG_OF_PROTEIN = "field field-type-number-decimal field-field-kcal";
    static String TAG_OF_KCAL = "field field-type-number-decimal field-field-kcal";
    static String TAG_OF_KCAL = "field field-type-number-decimal field-field-kcal";*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mStorageRef = FirebaseStorage.getInstance().getReference();
        URL url;
        InputStream inputStream;
        try {
            Toast.makeText(MainActivity.this, "LOL HZ", Toast.LENGTH_SHORT).show();
            url = new URL("http://www.calorizator.ru/sites/default/files/imagecache/product_96/product/ice-cone-burger-king.jpg");
            inputStream = url.openStream();

            UploadTask uploadTask = mStorageRef.child("images/1.jpg").putStream(inputStream);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, downloadUrl.toString());
                    startActivity(intent);
                }
            });

            /*mStorageRef.putStream(inputStream).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, downloadUrl.toString());
                    startActivity(intent);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("LOL", e.getLocalizedMessage());
                }
            });*/
        } catch (Exception e) {

        }





        /*AsyncLoad asyncLoad = new AsyncLoad();
        asyncLoad.execute();
        try {
            ArrayList<ItemOfGlobalBase> globalBases = asyncLoad.get();
            recyclerView.setAdapter(new ItemAdapter(globalBases));



            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("5");
            myRef.setValue(new GroupOfFood("Бургер Кинг", globalBases, "url"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvDesc, tvComposition, tvProp, tvCal, tvProt, tvFat, tvCarbo, tvUrl;

        public ItemHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.item, viewGroup, false));
            tvName = itemView.findViewById(R.id.tvName);
            tvDesc = itemView.findViewById(R.id.tvDescription);
            tvComposition = itemView.findViewById(R.id.tvComposition);
            tvProp = itemView.findViewById(R.id.tvProperties);
            tvCal = itemView.findViewById(R.id.tvCal);
            tvProt = itemView.findViewById(R.id.tvProtein);
            tvFat = itemView.findViewById(R.id.tvFat);
            tvCarbo = itemView.findViewById(R.id.tvCarbohydrates);
            tvUrl = itemView.findViewById(R.id.tvUrl);
        }

        public void bind(ItemOfGlobalBase itemOfGlobalBase) {
            tvName.setText(itemOfGlobalBase.getName());
            tvDesc.setText(itemOfGlobalBase.getDescription());
            tvComposition.setText(itemOfGlobalBase.getComposition());
            tvProp.setText(itemOfGlobalBase.getProperties());
            tvCal.setText(itemOfGlobalBase.getCalories());
            tvProt.setText(itemOfGlobalBase.getProtein());
            tvFat.setText(itemOfGlobalBase.getFat());
            tvCarbo.setText(itemOfGlobalBase.getCarbohydrates());
            tvUrl.setText(itemOfGlobalBase.getUrl_of_images());
        }
    }

    public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
        ArrayList<ItemOfGlobalBase> globalBases;

        public ItemAdapter(ArrayList<ItemOfGlobalBase> globalBases) {
            this.globalBases = globalBases;
        }

        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            return new ItemHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
            holder.bind(globalBases.get(position));
        }

        @Override
        public int getItemCount() {
            return globalBases.size();
        }
    }

    public static class AsyncLoad extends AsyncTask<Void, Void, ArrayList<ItemOfGlobalBase>> {
        @Override
        protected ArrayList<ItemOfGlobalBase> doInBackground(Void... voids) {
            String kcal, prot, fat, carbo, title, desc, properties, composition;
            ArrayList<ItemOfGlobalBase> globalBases = new ArrayList<>();
            try {
                Document doc = Jsoup.connect(url).userAgent(USER_AGENT).get();
                Elements elements = doc.select("div.views-field-field-picture-fid");

                //form array of links
                for (int i = firstElement; i <= lastElement; i++) {
                    Element element = elements.get(i);
                    String[] tempArray = element.html().split("\"");

                    Document docDetail = Jsoup.connect("http://www.calorizator.ru" + tempArray[3]).userAgent(USER_AGENT).get();
                    Elements elementsDetail = docDetail.select("div.field-items");
                    Element elementDetail = elementsDetail.get(1);

                    Elements allMainText = docDetail.select("p");

                    kcal = elementsDetail.get(1).html().split(TAG_DIV)[1];
                    prot = elementsDetail.get(2).html().split(TAG_DIV)[1];
                    fat = elementsDetail.get(3).html().split(TAG_DIV)[1];
                    carbo = elementsDetail.get(4).html().split(TAG_DIV)[1];
                    title = docDetail.select("h1").get(0).html();
                    desc = allMainText.get(0).ownText(); // only description - not supported
                    composition = allMainText.get(2).ownText(); // short description - not supported
                    properties = allMainText.get(1).ownText(); // kcal

                    globalBases.add(new ItemOfGlobalBase(title, desc, composition, properties, kcal, prot, fat, carbo, "url"));
                }
                return globalBases;
            } catch (IOException e) {
                return null;
            }

        }

        @Override
        protected void onPostExecute(ArrayList<ItemOfGlobalBase> globalBases) {
            super.onPostExecute(globalBases);
        }


    }
}
