package com.sea.academy.list.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public final class FileUtil {

    private FileUtil() {

    }

    public static Reader getFromAssets(Context context, String file) throws IOException {

        Validator.checkNull(context);
        Validator.checkNullEmpty(file);

        return new BufferedReader(
                new InputStreamReader(context.getResources().getAssets().open(file)));

    }

}
