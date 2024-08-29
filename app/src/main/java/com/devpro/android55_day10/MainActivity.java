package com.devpro.android55_day10;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.devpro.android55_day10.databases.DBHelper;
import com.devpro.android55_day10.models.Product;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);

//        Product product = new Product();
//        product.setTitle("Essence Mascara Lash Princess");
//        product.setPrice(19.99);
//        product.setDiscountPercentage(7.17);
//        product.setBrand("Essence");
//        product.setDescription("The Essence Mascara Lash Princess is a popular mascara known for its " +
//                "volumizing and lengthening effects.Achieve dramatic lashes with this long-lasting " +
//                "and cruelty-free formula.");
//        dbHelper.insertProduct(product);

        Log.d(TAG, "onCreate: "+dbHelper.getAllProduct().size());
    }
}