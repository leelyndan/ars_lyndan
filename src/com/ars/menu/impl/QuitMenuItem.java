package com.ars.menu.impl;

import com.ars.menu.IMenuItem;

public class QuitMenuItem implements IMenuItem
{

    @Override
    public void initilize()
    {
        System.exit(0);

    }

}
