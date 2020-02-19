package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.runes.*;
import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.runes.unique_runes.ONI;
import com.robertx22.mine_and_slash.database.runes.unique_runes.PSI;
import com.robertx22.mine_and_slash.database.runes.unique_runes.QAR;
import com.robertx22.mine_and_slash.database.runes.unique_runes.WOR;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;

import java.util.ArrayList;
import java.util.List;

public class Runes implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<BaseRune> All = new ArrayList<BaseRune>() {
            {
                {
                    add(new Cen(0));
                    add(new Mos(0));
                    add(new Ita(0));
                    add(new Ber(0));
                    add(new Dos(0));
                    add(new Goh(0));
                    add(new Rah(0));
                    add(new Voh(0));
                    add(new Xah(0));
                    add(new Ano(0));

                    add(new PSI());
                    add(new QAR());
                    add(new WOR());
                    add(new ONI());

                }

            }
        };

        All.forEach(x -> x.addToSerializables());

    }
}
