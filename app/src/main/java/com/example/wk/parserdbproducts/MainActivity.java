package com.example.wk.parserdbproducts;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wk.parserdbproducts.POJOForSaveIRL.OneItem;
import com.example.wk.parserdbproducts.POJOS.GroupOfFood;
import com.example.wk.parserdbproducts.POJOS.ItemOfGlobalBase;
import com.example.wk.parserdbproducts.POJOS.ListOfGroupsFood;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {


    private DatabaseReference mDatabase;
    String TAG = "loadTest";
    private ListOfGroupsFood listOfGroupsFood;

    static String url = "http://www.calorizator.ru/product/pix?page=26";
    static int firstElement = 0;
    static int lastElement = 70;
    static String USER_AGENT = "Mozilla";
    static String TAG_DIV = "</div>";
    static String NAME_OF_GROUP = "Японская кухня";
    String NAME_OF_ENTITY = "60";

    ArrayList<ItemOfGlobalBase> globalBases;
    ArrayList<ItemOfGlobalBase> globalBasesWithPreparedLinks;

    ArrayList<GroupOfFood> allItems = new ArrayList<>();
    ArrayList<ListOfGroupsFood> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allItems = new ArrayList<>();



        //readAndWrite();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("dbAnalyzer");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

               listOfGroupsFood = dataSnapshot.getValue(ListOfGroupsFood.class);


                if (listOfGroupsFood == null){

                    Log.d(TAG, "null");
                } else {

                    Log.d(TAG, "loaded!!!");
                }

                Log.d(TAG, String.valueOf(listOfGroupsFood.getListOfGroupsOfFood().size()));
//                for (GroupOfFood food: listOfGroupsFood.getListOfGroupsOfFood()) {
//                    Log.d(TAG, food.getName());
//                }

                String prim = "Многие почитатели сладкого любят побаловать себя нежным " +
                        "шоколадным пирожным Брауни. Название  десерта происходит от английского" +
                        " слова , что в переводе означает . Впервые Брауни было упомянуто в 1896 " +
                        "году в кулинарной книге Фанни Фармер. Тогда  это название носили пирожные " +
                        "из мелассы. Со временем в рецепт вносились дополнения и изменения и теперь " +
                        "этот десерт может иметь как консистенцию торта, так и кекса или печенья. В " +
                        "ресторанах быстрого питания среди десертов также есть пирожное Брауни. Тут " +
                        "оно подается горячим или же с добавлением мороженого.";

//                Log.d(TAG, cutX(prim));

                int index = 0;

               // listOfGroupsFood.getListOfGroupsOfFood().get(0).getListOfFoodItems().get(0).setDescription(cutX( listOfGroupsFood.getListOfGroupsOfFood().get(0).getListOfFoodItems().get(0).getDescription()));


                for (int i = 0; i < listOfGroupsFood.getListOfGroupsOfFood().size() - 1; i++){


                    for (int y = 0; y < listOfGroupsFood.getListOfGroupsOfFood().get(i).getListOfFoodItems().size() - 1; y++){

                        String description = cutX(listOfGroupsFood.getListOfGroupsOfFood().get(i).getListOfFoodItems().get(y).getDescription());
                        String composition = cutX(listOfGroupsFood.getListOfGroupsOfFood().get(i).getListOfFoodItems().get(y).getComposition());
                        String properties = cutX(listOfGroupsFood.getListOfGroupsOfFood().get(i).getListOfFoodItems().get(y).getProperties());

                        listOfGroupsFood.getListOfGroupsOfFood().get(i).getListOfFoodItems().get(y).setDescription(description);
                        listOfGroupsFood.getListOfGroupsOfFood().get(i).getListOfFoodItems().get(y).setComposition(composition);
                        listOfGroupsFood.getListOfGroupsOfFood().get(i).getListOfFoodItems().get(y).setProperties(properties);
                    }

                }


                Log.d(TAG, listOfGroupsFood.getListOfGroupsOfFood().get(0).getListOfFoodItems().get(0).getDescription());

//                for (GroupOfFood logf:
//                        listOfGroupsFood.getListOfGroupsOfFood()) {
//
//                    for (ItemOfGlobalBase iogb:
//                         logf.getListOfFoodItems()) {
//
//                        String description = iogb.getDescription();
//                        String composition = iogb.getComposition();
//                        String properties = iogb.getProperties();
//
//                        iogb.setDescription(cutX(description));
//                        iogb.setComposition(cutX(composition));
//                        iogb.setProperties(cutX(properties));
//                        index++;
//                    }
//                }





                Log.d(TAG, "index: " + index);
                Log.d(TAG, "fINALLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL!!!");




//                for (int i = 0; i < listOfGroupsFood.getListOfGroupsOfFood().size() - 1; i++){
////                    Log.d(TAG, "FOR");
//                    if (listOfGroupsFood.getListOfGroupsOfFood().get(i).getName().equals(listOfGroupsFood.getListOfGroupsOfFood().get(i + 1).getName()) ){
//                        sum(listOfGroupsFood.getListOfGroupsOfFood().get(i), listOfGroupsFood.getListOfGroupsOfFood().get(i + 1));
//                        listOfGroupsFood.getListOfGroupsOfFood().remove(i + 1);
//                        i--;
//                        Log.d(TAG, String.valueOf(listOfGroupsFood.getListOfGroupsOfFood().size()));
//                    }
//                }


//                for (GroupOfFood food: listOfGroupsFood.getListOfGroupsOfFood()) {
//                    Log.d(TAG, "+ " + food.getName());
//                }

//                Log.d(TAG, "poeben'" + String.valueOf(listOfGroupsFood.getListOfGroupsOfFood().get(listOfGroupsFood.getListOfGroupsOfFood().size()-1).getListOfFoodItems().size()));
                saveinDB();


                Log.d(TAG, "SAVE!!!");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    private String cutX(String text){

        String regular = ".*\\s[,.!?\\s].*";

        final String sentences[] = text.split("[.!?]\\s*");

        String result = "";

        for (String str:
                sentences) {
            str = str + ".";
            if (str.matches(regular)){
//                Log.d(TAG,"!!!!" + str);
                return result;
            } else {
                result = result + str;
            }

        }

//        for (String s:
//                sentences) {
//            Log.d(TAG, s);
//        }

        return result;
    }


    private void sum(GroupOfFood main, GroupOfFood second){

        main.getListOfFoodItems().addAll(second.getListOfFoodItems());
    }


    private void saveinDB(){

        mDatabase = FirebaseDatabase.getInstance().getReference();

            mDatabase.child("dbAnalyzer").setValue(listOfGroupsFood);


    }



    private ArrayList<ItemOfGlobalBase> fillItemsList(ListOfGroupsFood listOfGroupsFood) {
        ItemOfGlobalBase itemForGroupNaming;
        ArrayList<ItemOfGlobalBase> items = new ArrayList<>();
        for (int i = 0; i < listOfGroupsFood.getListOfGroupsOfFood().size(); i++) {
            itemForGroupNaming = new ItemOfGlobalBase(listOfGroupsFood.getListOfGroupsOfFood().get(i).getName(),
                    "0", "0", "0", "0",
                    "0", "0", "0","0");

            items.add(itemForGroupNaming);
            ItemOfGlobalBase itemOfGlobalBaseForWriting;
            for (int j = 0; j < listOfGroupsFood.getListOfGroupsOfFood().get(i).getListOfFoodItems().size(); j++) {
                itemOfGlobalBaseForWriting = listOfGroupsFood.getListOfGroupsOfFood().get(i).getListOfFoodItems().get(j);
                items.add(itemOfGlobalBaseForWriting);
            }

        }
        Log.e("LOL", String.valueOf(items.size()));
        return items;
    }

    private void readWithNullElementsAndSaveInLocaleList(final String number) {

        Log.e("LOL", "Start");
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("analyzer");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.add(dataSnapshot.getValue(ListOfGroupsFood.class));
                String i = list.get(0).getListOfGroupsOfFood().get(0).getListOfFoodItems().get(0).getFat().split(" ")[1];
                Log.e("LOL", "Base loaded");

                ListOfGroupsFood newListOfGroupsFood = new ListOfGroupsFood();
                ListOfGroupsFood oldListOfGroupsFood = list.get(0);
                ArrayList<GroupOfFood> newListOfGroupFoodForWrite = new ArrayList<>();
                for (int j = 0; j < list.get(0).getListOfGroupsOfFood().size(); j++) {
                    GroupOfFood oldGroupOfFood = list.get(0).getListOfGroupsOfFood().get(j);
                    GroupOfFood newGroupOfFood = new GroupOfFood();
                    ArrayList<ItemOfGlobalBase> listOfItemsForWrite = new ArrayList<>();

                    for (int k = 0; k < list.get(0).getListOfGroupsOfFood().get(j).getListOfFoodItems().size(); k++) {
                        ItemOfGlobalBase oldItem = list.get(0).getListOfGroupsOfFood().get(j).getListOfFoodItems().get(k);
                        ItemOfGlobalBase newItem = new ItemOfGlobalBase();
                        newItem.setName(oldItem.getName());
                        newItem.setDescription(oldItem.getDescription());
                        newItem.setComposition(oldItem.getComposition());
                        newItem.setProperties(oldItem.getProperties());
                        newItem.setUrl_of_images(oldItem.getUrl_of_images());

                        newItem.setCalories(oldItem.getCalories().split(" ")[1]);

                        newItem.setProtein(oldItem.getProtein().split(" ")[1]);

                        newItem.setFat(oldItem.getFat().split(" ")[1]);
                        if (oldItem.getCarbohydrates().split(" ").length != 1) {
                            newItem.setCarbohydrates(oldItem.getCarbohydrates().split(" ")[1]);
                        } else {
                            newItem.setCarbohydrates("0");
                        }
                        double cal = Double.parseDouble(newItem.getCalories());
                        double prot = Double.parseDouble(newItem.getProtein());
                        double fat = Double.parseDouble(newItem.getFat());
                        double car = Double.parseDouble(newItem.getCarbohydrates());

                        listOfItemsForWrite.add(newItem);
                    }

                    newGroupOfFood.setName(oldGroupOfFood.getName());
                    newGroupOfFood.setUrl_of_image(oldGroupOfFood.getUrl_of_image());
                    newGroupOfFood.setListOfFoodItems(listOfItemsForWrite);

                    Log.e("LOL", newGroupOfFood.getName() + "   "
                            + newListOfGroupFoodForWrite.size() + "size -- "
                            + String.valueOf(listOfItemsForWrite.size()));
                    newListOfGroupFoodForWrite.add(newGroupOfFood);
                }
                newListOfGroupsFood.setName("dbAnalyzer");
                newListOfGroupsFood.setListOfGroupsOfFood(newListOfGroupFoodForWrite);
                list.add(newListOfGroupsFood);
                Log.e("LOL", "DB added to LIST");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void readAndWrite() {
        AsyncLoad asyncLoad = new AsyncLoad();
        asyncLoad.execute();
        try {
            globalBases = asyncLoad.get();
            Toast.makeText(this, "Loaded all data. Start to rewrite DB", Toast.LENGTH_SHORT).show();
            Log.i("LOL", "Loaded all data. Start to rewrite DB");
            AsyncLoadUrl asyncLoadUrl = new AsyncLoadUrl();
            asyncLoadUrl.execute();
            globalBasesWithPreparedLinks = asyncLoadUrl.get();

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference(NAME_OF_ENTITY);
            myRef.setValue(new GroupOfFood(NAME_OF_GROUP, globalBasesWithPreparedLinks, "url"));


            FirebaseDatabase databaseForSaveIRL = FirebaseDatabase.getInstance();
            DatabaseReference myRefForSaveIRL = databaseForSaveIRL.getReference("a" + NAME_OF_ENTITY);
            myRefForSaveIRL.setValue(new OneItem(NAME_OF_GROUP, String.valueOf(firstElement), String.valueOf(lastElement), url));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvCal, tvProt, tvFat, tvCarbo, tvNumber;
        private ImageView ivMainImage;

        public ItemHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.item, viewGroup, false));
            tvName = itemView.findViewById(R.id.tvName);
            tvCal = itemView.findViewById(R.id.tvCal);
            tvProt = itemView.findViewById(R.id.tvProtein);
            tvFat = itemView.findViewById(R.id.tvFat);
            tvCarbo = itemView.findViewById(R.id.tvCarbohydrates);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            ivMainImage = itemView.findViewById(R.id.ivImage);
        }

        public void bind(ItemOfGlobalBase itemOfGlobalBase, boolean isItemForSeparator) {
            if (isItemForSeparator){
                tvCal.setVisibility(View.GONE);
                tvProt.setVisibility(View.GONE);
                tvFat.setVisibility(View.GONE);
                tvCarbo.setVisibility(View.GONE);
                tvName.setText(itemOfGlobalBase.getName());
            }else {
                tvName.setText(itemOfGlobalBase.getName());
                tvCal.setText(itemOfGlobalBase.getCalories());
                tvProt.setText(itemOfGlobalBase.getProtein());
                tvFat.setText(itemOfGlobalBase.getFat());
                tvCarbo.setText(itemOfGlobalBase.getCarbohydrates());
                tvNumber.setText(String.valueOf(getAdapterPosition()));
                Glide.with(MainActivity.this).load(itemOfGlobalBase.getUrl_of_images()).into(ivMainImage);
            }

        }
    }

    public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
        ArrayList<ItemOfGlobalBase> itemsOfGlobalBases;

        public ItemAdapter(ArrayList<ItemOfGlobalBase> itemsOfGlobalBases) {
            this.itemsOfGlobalBases = itemsOfGlobalBases;
        }

        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            return new ItemHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
            if (itemsOfGlobalBases.get(position).getUrl_of_images().equals(null)){
                holder.bind(itemsOfGlobalBases.get(position), true);
            }else {
                holder.bind(itemsOfGlobalBases.get(position), false);
            }

        }

        @Override
        public int getItemCount() {
            return itemsOfGlobalBases.size();
        }
    }

    public static class AsyncLoad extends AsyncTask<Void, Void, ArrayList<ItemOfGlobalBase>> {
        @Override
        protected ArrayList<ItemOfGlobalBase> doInBackground(Void... voids) {
            String kcal, prot, fat, carbo, title, desc, properties, composition, urlForLoadImageRaw;
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

                    urlForLoadImageRaw = tempArray[7];
                    kcal = elementsDetail.get(1).html().split(TAG_DIV)[1];
                    prot = elementsDetail.get(2).html().split(TAG_DIV)[1];
                    fat = elementsDetail.get(3).html().split(TAG_DIV)[1];
                    if (elementsDetail.size() >= 5) {
                        carbo = elementsDetail.get(4).html().split(TAG_DIV)[1];
                    } else {
                        carbo = "";
                    }
                    title = docDetail.select("h1").get(0).html();
                    desc = allMainText.get(0).ownText();
                    if (allMainText.size() >= 2) {
                        properties = allMainText.get(1).ownText();
                    } else {
                        properties = "";
                    }

                    if (allMainText.size() >= 3) {
                        composition = allMainText.get(2).ownText();
                    } else {
                        composition = "";
                    }

                    globalBases.add(new ItemOfGlobalBase(title, desc, composition, properties, kcal, prot, fat, carbo, urlForLoadImageRaw));
                    Log.i("LOL", String.valueOf(i) + " загружен с сайта.");
                }
                return globalBases;
            } catch (Exception e) {
                return null;
            }

        }

        @Override
        protected void onPostExecute(ArrayList<ItemOfGlobalBase> globalBases) {
            super.onPostExecute(globalBases);
        }


    }

    public class AsyncLoadUrl extends AsyncTask<Void, Void, ArrayList<ItemOfGlobalBase>> {
        @Override
        protected ArrayList<ItemOfGlobalBase> doInBackground(Void... voids) {
            ArrayList<ItemOfGlobalBase> preparedArray = new ArrayList<>();
            for (int i = 0; i < globalBases.size(); i++) {
                try {
                    ItemOfGlobalBase rewriteItemOfGB = new ItemOfGlobalBase();
                    InputStream inputStream
                            = new URL(globalBases.get(i).getUrl_of_images())
                            .openStream();
                    FirebaseStorage storage = FirebaseStorage.getInstance();
                    StorageReference mStorageRef = storage.getReference();
                    StorageReference ref = mStorageRef.child("images/" + NAME_OF_GROUP + "/" + globalBases.get(i).getName().replace("/", "-") + ".jpg");
                    UploadTask uploadTask = ref.putStream(inputStream);
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        }
                    });

                    while (!uploadTask.isSuccessful()) {
                        Log.i("LOL", "Ждем, пишем " + String.valueOf(i) + "-й элемент");
                    }

                    rewriteItemOfGB.setUrl_of_images(uploadTask.getSnapshot().getDownloadUrl().toString());
                    rewriteItemOfGB.setName(globalBases.get(i).getName());
                    rewriteItemOfGB.setComposition(globalBases.get(i).getComposition());
                    rewriteItemOfGB.setDescription(globalBases.get(i).getDescription());
                    rewriteItemOfGB.setProperties(globalBases.get(i).getProperties());
                    rewriteItemOfGB.setCalories(globalBases.get(i).getCalories());
                    rewriteItemOfGB.setCarbohydrates(globalBases.get(i).getCarbohydrates());
                    rewriteItemOfGB.setFat(globalBases.get(i).getFat());
                    rewriteItemOfGB.setProtein(globalBases.get(i).getProtein());
                    preparedArray.add(rewriteItemOfGB);
                    Log.i("LOL", String.valueOf(i) + " загружен в хранилище и добавлен в массив");


                } catch (Exception e) {
                    Log.e("LOL", e.getMessage());
                }
            }
            return preparedArray;
        }

        @Override
        protected void onPostExecute(ArrayList<ItemOfGlobalBase> array) {
            super.onPostExecute(array);


        }
    }
}
