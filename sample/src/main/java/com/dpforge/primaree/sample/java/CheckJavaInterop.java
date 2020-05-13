package com.dpforge.primaree.sample.java;

import android.app.Application;

import com.dpforge.primaree.ApplicationExtensions;
import com.dpforge.primaree.Primaree;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/**
 * The only purpose of this class is to check that Java-interop is not broken
 */
@SuppressWarnings("unused")
class CheckJavaInterop {

    void check(Application application) {
        String name = Primaree.getCurrentProcessFullName();
        System.out.println(name);

        ApplicationExtensions.runIfPrimaryProcess(application, new Function0<Unit>() {
            @Override
            public Unit invoke() {
                return null;
            }
        });

        if (ApplicationExtensions.isPrimaryProcess(application)) {
            System.out.println("This is the primary process");
        }
    }

}
