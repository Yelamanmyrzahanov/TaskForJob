package kz.djunglestones.taskforjob;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.SearchView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private String URL_JSON = "http://www.mocky.io/v2/5a488f243000004c15c3c57e";
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;

    private List<User> list_user;
    private RecyclerView my_recyclerView;

    private SearchView searchView;

    private Toolbar mainToolbar;

    private RvAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Users");

        list_user = new ArrayList<>();
        my_recyclerView = findViewById(R.id.recycler_view);
        jsonCall();

//        FetchData process = new FetchData();
//        process.execute();





    }

//

    private void jsonCall() {


        request = new JsonArrayRequest(URL_JSON, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;


                for (int i = 0 ; i<response.length();i++) {

                    //Toast.makeText(getApplicationContext(),String.valueOf(i),Toast.LENGTH_SHORT).show();

                    try {

                        jsonObject = response.getJSONObject(i);
                        String employment = jsonObject.getString("employment");
                        JSONObject employmentObject = new JSONObject(employment);
                        User user = new User();

                        user.setId(jsonObject.getInt("id"));
                        user.setFirst_name(jsonObject.getString("first_name"));
                        user.setLast_name(jsonObject.getString("last_name"));
                        user.setEmail(jsonObject.getString("email"));
                        user.setGender(jsonObject.getString("gender"));
                        user.setIp_address(jsonObject.getString("ip_address"));
                        user.setPhoto(jsonObject.getString("photo"));
                        user.setName(employmentObject.getString("name"));
                        user.setPosition(employmentObject.getString("position"));
                        list_user.add(user);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


//                Toast.makeText(MainActivity.this,"Size of Liste "+String.valueOf(list_user.size()),Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this,list_user.get(1).toString(),Toast.LENGTH_SHORT).show();

                setRvadapter(list_user);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }

    private void setRvadapter(List<User> lst) {

        myAdapter = new RvAdapter(this,lst) ;
        my_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        my_recyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);

        final MenuItem menuItem = menu.findItem(R.id.menuSearch);
        searchView = (SearchView) menuItem.getActionView();
        ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setHintTextColor(getResources().getColor(R.color.white));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (!searchView.isIconified()){
                    searchView.setIconified(true);
                }
                menuItem.collapseActionView();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<User> filterModelList = filter(list_user,newText);
                myAdapter.setFilter(filterModelList);
                return true;
            }
        });

        return true;

    }

    private List<User> filter(List<User> list_user,String query){
        query = query.toLowerCase();
        final  List<User> filteredserList = new ArrayList<>();
        for (User model:list_user){
            final String text = model.getFirst_name().toLowerCase();
            if (text.startsWith(query)){
                filteredserList.add(model);
            }
        }
        return filteredserList;
    }
}
