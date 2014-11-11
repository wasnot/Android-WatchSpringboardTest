package net.wasnot.android.watchspringboard;

import net.wasnot.android.watchspringboard.view.LauncherView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    private LauncherView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.view).requestLayout();
            }
        });

        mView = (LauncherView) findViewById(R.id.action_settings);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        [[self customView].respringButton addTarget:self action:@selector(LM_respringTapped:) forControlEvents:UIControlEventTouchUpInside];
//        [self springboard].alpha = 0;
//
//        for(LMSpringboardItemView* item in [self springboard].itemViews)
//        {
//            UITapGestureRecognizer* tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(LM_iconTapped:)];
//            tap.numberOfTapsRequired = 1;
//            tap.delegate = self;
//            [item addGestureRecognizer:tap];
//        }
    }

//    #pragma mark Notifications

//    public void LM_didBecomeActive()
//    {
//        if(!mView.isAppLaunched())
//        {
//            [[self springboard] centerOnIndex:0 zoomScale:1 animated:NO];
//            [[self springboard] doIntroAnimation];
//            [self springboard].alpha = 1;
//        }
//    }

//    public void LM_didEnterBackground()
//    {
//        if([self customView].isAppLaunched == NO)
//        [self springboard].alpha = 0;
//    }

//    #pragma mark UIGestureRecognizerDelegate

//    public boolean gestureRecognizer:(UIGestureRecognizer *)gestureRecognizer shouldReceiveTouch:(UITouch *)touch
//    {
//        if([self springboard].zoomScale < [self springboard].minimumZoomLevelToLaunchApp)
//        return NO;
//        return YES;
//    }

//    #pragma mark - Input

//    public void LM_respringTapped(id sender)
//    {
//        if([self customView].isAppLaunched == YES)
//        {
//            [[self customView] quitApp];
//            [UIView animateWithDuration:0.3 animations:^{
//            [self setNeedsStatusBarAppearanceUpdate];
//        }];
//        }
//        else
//        {
//            LMSpringboardView* springboard = [self springboard];
//            [UIView animateWithDuration:0.3 animations:^{
//            springboard.alpha = 0;
//        } completion:^(BOOL finished) {
//            [springboard doIntroAnimation];
//            springboard.alpha = 1;
//        }];
//        }
//    }

//    public void LM_iconTapped(UITapGestureRecognizer sender)
//    {
//        View item = sender.view;
//        while(item != nil && [item isKindOfClass:[LMSpringboardItemView class]] == NO)
//        item = item.superview;
//        [[self customView] launchAppItem:(LMSpringboardItemView*)item];
//
//        Animation animation = new Animation(){} ;
//        animation.setDuration(500);
//        item.startAnimation(animation);
//        [UIView animateWithDuration:0.5 animations:^{
//        [self setNeedsStatusBarAppearanceUpdate];
//    }];
//    }

//    #pragma mark - UIViewController

//    - (void)viewWillAppear:(BOOL)animated
//    {
//        [super viewWillAppear:animated];
//
//        [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(LM_didBecomeActive) name:UIApplicationDidBecomeActiveNotification object:nil];
//        [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(LM_didEnterBackground) name:UIApplicationDidEnterBackgroundNotification object:nil];
//
//        LMSpringboardView* springboard = [self springboard];
//        [springboard centerOnIndex:0 zoomScale:springboard.zoomScale animated:NO];
//    }

//    - (void)viewWillDisappear:(BOOL)animated
//    {
//        [super viewWillDisappear:animated];
//
//        [[NSNotificationCenter defaultCenter] removeObserver:self name:UIApplicationDidBecomeActiveNotification object:nil];
//        [[NSNotificationCenter defaultCenter] removeObserver:self name:UIApplicationDidEnterBackgroundNotification object:nil];
//    }

//    - (UIStatusBarStyle)preferredStatusBarStyle
//    {
//        if([self isViewLoaded] == YES && [self customView].isAppLaunched == YES)
//        return UIStatusBarStyleDefault;
//        else
//        return UIStatusBarStyleLightContent;
//    }

}
