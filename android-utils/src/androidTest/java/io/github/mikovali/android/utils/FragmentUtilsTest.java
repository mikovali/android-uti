package io.github.mikovali.android.utils;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.github.mikovali.android.test.ActivityRule;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(AndroidJUnit4.class)
public class FragmentUtilsTest {

    @Rule
    public final ActivityRule<Activity> activity = new ActivityRule<>(Activity.class);
    @Rule
    public final ActivityRule<ParentActivity> parentActivity = new ActivityRule<>(ParentActivity.class);
    @Rule
    public final ActivityRule<ParentFragmentActivity> parentFragmentActivity = new ActivityRule<>(ParentFragmentActivity.class);

    private Application application;

    private Fragment fragment;
    private Fragment attachedFragment;
    private Fragment attachedToParentActivityFragment;
    private Fragment parentFragment;
    private Fragment fragmentWithParentFragment;

    @Before
    public void setUp() {
        application = activity.get().getApplication();
        fragment = new Fragment();
        attachedFragment = activity.get().getSupportFragmentManager()
                .findFragmentById(android.R.id.content);
        attachedToParentActivityFragment = parentActivity.get().getSupportFragmentManager()
                .findFragmentById(android.R.id.content);
        parentFragment = parentFragmentActivity.get().getSupportFragmentManager()
                .findFragmentById(android.R.id.content);
        fragmentWithParentFragment = parentFragment.getChildFragmentManager()
                .findFragmentById(android.R.id.content);
    }

    @Test
    public void getParentAsWithApplicationReturnsNullForNewFragments() {
        assertThat(FragmentUtils.getParentAs(fragment, Application.class))
                .isNull();
    }

    @Test
    public void getParentAsReturnsNullWhenTypeNotFoundInHierarchy() {
        assertThat(FragmentUtils.getParentAs(attachedFragment, Parent.class))
                .isNull();
    }

    @Test
    public void getParentAsReturnsApplication() {
        assertThat(FragmentUtils.getParentAs(attachedFragment, Application.class))
                .isSameAs(application);
    }

    @Test
    public void getParentAsReturnsParentActivity() {
        assertThat(FragmentUtils.getParentAs(attachedToParentActivityFragment, Parent.class))
                .isSameAs(parentActivity.get());
    }

    @Test
    public void getParentAsReturnsParentFragment() {
        assertThat(FragmentUtils.getParentAs(fragmentWithParentFragment, Parent.class))
                .isSameAs(parentFragment);
    }

    public interface Parent {
    }

    public static class Activity extends FragmentActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, getContentFragment())
                        .commit();
            }
        }

        protected Fragment getContentFragment() {
            return new Fragment();
        }
    }

    public static class ParentActivity extends Activity implements Parent {
    }

    public static class ParentFragmentActivity extends  ParentActivity {

        @Override
        protected Fragment getContentFragment() {
            return new ParentFragment();
        }
    }

    public static class ParentFragment extends Fragment implements Parent {

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            final View view = new FrameLayout(inflater.getContext());
            view.setId(android.R.id.content);
            return view;
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            if (getChildFragmentManager().findFragmentById(android.R.id.content) == null) {
                getChildFragmentManager().beginTransaction()
                        .add(android.R.id.content, new Fragment())
                        .commit();
            }
        }
    }
}
