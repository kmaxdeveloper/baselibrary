package uz.kmax.baselibrarys.basefragment;

import android.widget.TextView;
import android.widget.Toast;
import uz.kmax.base.basefragment.BaseFragmentJava;
import uz.kmax.baselibrarys.R;

/***
 *  Kmax Developer - 2022.12.16
 *  Typing in Java
 *  Made In Uzbekistan
 */

public class JavaFragment extends BaseFragmentJava {

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_main2; // set Current Layout
    }

    @Override
    public void onViewCreated() {
        // You Can called View Fields
        TextView text = view.findViewById(R.id.text);

        text.setOnClickListener(view -> {
            Toast toast=Toast.makeText(requireContext(),"Hello Base Fragment Java",Toast.LENGTH_SHORT);
            toast.setMargin(50,50);
            toast.show();
        });
    }
}
