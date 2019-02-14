package com.theolympian.alu.alumnimembers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.theolympian.alu.R;
import com.theolympian.alu.alumnimembers.Model.DataItem;
import com.theolympian.alu.alumnimembers.Model.SubCategoryItem;
import com.theolympian.alu.alumnimembers.utils.ConstantManager;

import java.util.ArrayList;
import java.util.HashMap;

public class AlumniMembersActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ExpandableListView lvCategory;
    private ArrayList<DataItem> arCategory;
    private ArrayList<SubCategoryItem> arSubCategory;
    private ArrayList<ArrayList<SubCategoryItem>> arSubCategoryFinal;

    private ArrayList<HashMap<String, String>> parentItems;
    private ArrayList<ArrayList<HashMap<String, String>>> childItems;
    private FilterCategoriesExpandableListAdapter myCategoriesExpandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnimembers);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setupReferences() {

        lvCategory = findViewById(R.id.lvCategory);
        arCategory = new ArrayList<>();
        arSubCategory = new ArrayList<>();
        parentItems = new ArrayList<>();
        childItems = new ArrayList<>();

        DataItem dataItem = new DataItem();

        //Remove these hardcoded logic with the actual backend data
        addBatchCategory(dataItem);

        addBranchCategory(dataItem);

        addSkillCategory(dataItem);


        addRegionCategory(dataItem);


        Log.d("TAG", "setupReferences: "+arCategory.size());

        for(DataItem data : arCategory){
//                        Log.i("Item id",item.id);
            ArrayList<HashMap<String, String>> childArrayList =new ArrayList<HashMap<String, String>>();
            HashMap<String, String> mapParent = new HashMap<String, String>();

            mapParent.put(ConstantManager.Parameter.CATEGORY_ID,data.getCategoryId());
            mapParent.put(ConstantManager.Parameter.CATEGORY_NAME,data.getCategoryName());

            int countIsChecked = 0;
            for(SubCategoryItem subCategoryItem : data.getSubCategory()) {

                HashMap<String, String> mapChild = new HashMap<String, String>();
                mapChild.put(ConstantManager.Parameter.SUB_ID,subCategoryItem.getSubId());
                mapChild.put(ConstantManager.Parameter.SUB_CATEGORY_NAME,subCategoryItem.getSubCategoryName());
                mapChild.put(ConstantManager.Parameter.CATEGORY_ID,subCategoryItem.getCategoryId());
                mapChild.put(ConstantManager.Parameter.IS_CHECKED,subCategoryItem.getIsChecked());

                if(subCategoryItem.getIsChecked().equalsIgnoreCase(ConstantManager.CHECK_BOX_CHECKED_TRUE)) {

                    countIsChecked++;
                }
                childArrayList.add(mapChild);
            }

            if(countIsChecked == data.getSubCategory().size()) {

                data.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_TRUE);
            }else {
                data.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            }

            mapParent.put(ConstantManager.Parameter.IS_CHECKED,data.getIsChecked());
            childItems.add(childArrayList);
            parentItems.add(mapParent);

        }

        ConstantManager.parentItems = parentItems;
        ConstantManager.childItems = childItems;

        myCategoriesExpandableListAdapter = new FilterCategoriesExpandableListAdapter(this,parentItems,childItems,false);
        lvCategory.setAdapter(myCategoriesExpandableListAdapter);
    }

    private void addBatchCategory(DataItem dataItem) {
        dataItem.setCategoryId("1");
        dataItem.setCategoryName("Batch");

        arSubCategory = new ArrayList<>();
        for(int i = 0; i < 4; i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(2015 + i +"");
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);
    }

    private void addBranchCategory(DataItem dataItem) {
        dataItem = new DataItem();
        dataItem.setCategoryId("2");
        dataItem.setCategoryName("Branch");
        arSubCategory = new ArrayList<>();
        for(int j = 0; j < 6 ; j++) {
            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(j));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            String branchName = "CSE";
            switch (j){
                case 0:
                    branchName = "CSE";
                    break;
                case 1:
                    branchName = "ECE";
                    break;
                case 2:
                    branchName = "IT";
                    break;
                case 3:
                    branchName = "MECH";
                    break;
                case 4:
                    branchName = "EEE";
                    break;
            }
            subCategoryItem.setSubCategoryName(branchName);
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

    }

    private void addSkillCategory(DataItem dataItem) {
        dataItem = new DataItem();
        dataItem.setCategoryId("3");
        dataItem.setCategoryName("Skill");
        arSubCategory = new ArrayList<>();
        for(int k = 0; k < 2; k++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(k));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            String skills = "Developer";
            switch (k) {
                case 0:
                    skills = "Developer";
                    break;
                case 1:
                    skills = "Tester";
                    break;
            }

            subCategoryItem.setSubCategoryName(skills);
            arSubCategory.add(subCategoryItem);
        }

        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);
    }

    private void addRegionCategory(DataItem dataItem) {
        dataItem = new DataItem();
        dataItem.setCategoryId("4");
        dataItem.setCategoryName("Region");
        arSubCategory = new ArrayList<>();
        for(int k = 0; k < 7; k++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(k));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            String region = "Chennai";
            switch (k) {
                case 0:
                    region = "Chennai";
                    break;
                case 1:
                    region = "Kovilpatti";
                    break;
                case 2:
                    region = "Madurai";
                    break;
                case 3:
                    region = "Nagerkoil";
                    break;
                case 4:
                    region = "Tirunelveli";
                    break;
                case 5:
                    region = "Coimbatore";
                    break;
                case 6:
                    region = "Salem";
                    break;
            }

            subCategoryItem.setSubCategoryName(region);
            arSubCategory.add(subCategoryItem);
        }

        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        MenuItem filterItem = menu.findItem(R.id.action_filter);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_filter:
                loadFilterRecylerView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadFilterRecylerView() {
        setupReferences();
    }
}