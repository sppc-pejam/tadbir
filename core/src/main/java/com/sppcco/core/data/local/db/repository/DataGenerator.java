package com.sppcco.core.data.local.db.repository;

import android.content.res.Resources;

import com.sppcco.core.data.model.TablesStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by m_pejam on 01/24/18.
 * DataGenerator
 */

public class DataGenerator {

    public static final int TABLE_NAME_ARRAY_COUNT = 11;
    public static final int TABLE_NAME_IN_ARRAY = 0;
    public static final int TABLE_ENGLISH_NAME_IN_ARRAY = 1;
    public static final int TABLE_PERSIAN_NAME_IN_ARRAY = 2;

//TODO
/*
     Resources r = UApp.getAppContext().getResources();
        String[][] tablesName = new String[TABLE_NAME_ARRAY_COUNT][];
        tablesName[0] = r.getStringArray(R.array.customer);
        tablesName[1] = r.getStringArray(R.array.merchandise);
        tablesName[2] = r.getStringArray(R.array.unit);
        tablesName[3] = r.getStringArray(R.array.sales_price);
        tablesName[4] = r.getStringArray(R.array.account);
        tablesName[5] = r.getStringArray(R.array.cost_center);
        tablesName[6] = r.getStringArray(R.array.detail_acc);
        tablesName[7] = r.getStringArray(R.array.project);
        tablesName[8] = r.getStringArray(R.array.stock_room);
        tablesName[9] = r.getStringArray(R.array.cabinet);
        tablesName[10] = r.getStringArray(R.array.merch_stock);

        List<TablesStatus> tablesStatuses = new ArrayList<>(TABLE_NAME_ARRAY_COUNT);

        for (int i = 0; i < TABLE_NAME_ARRAY_COUNT; i++) {

            TablesStatus tablesStatus = new TablesStatus();
            tablesStatus.setTableName(tablesName[i][TABLE_NAME_IN_ARRAY]);
            tablesStatus.setEnglishName(tablesName[i][TABLE_ENGLISH_NAME_IN_ARRAY]);
            tablesStatus.setPersianName(tablesName[i][TABLE_PERSIAN_NAME_IN_ARRAY]);
            tablesStatus.setLastUpdateDate("");
            tablesStatus.setLastUpdateRowCount(0);
            tablesStatus.setFPId(0);

            tablesStatuses.add(tablesStatus);
        }
        return tablesStatuses;
    }*/
}
