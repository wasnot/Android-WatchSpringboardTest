package net.wasnot.android.watchspringboard.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Size;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by akihiroaida on 2014/10/30.
 */
public class LauncherView extends View {

    public LauncherView(Context context) {
        super(context);
    }

    public LauncherView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LauncherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public WatchSpringboardView _springboard;
    public View _appView;
    public Button _respringButton;
    public boolean _isAppLaunched;

    private ImageView _appLaunchMaskView;
    private WatchSpringboardItemView _lastLaunchedItem;


    public void launchAppItem(WatchSpringboardItemView item) {
        if (!_isAppLaunched) {
            _isAppLaunched = true;
            _lastLaunchedItem = item;

//            Point pointInSelf = [self convertPoint:item.icon.center fromView:item];
//            Point pointInSelf = this.inset//[self convertPoint:item.icon.center fromView:item];
//            float dx = pointInSelf.x-_appView.center.x;
//            float dy = pointInSelf.y-_appView.center.y;
//
//            double appScale = 60*item.scale/Math.min(_appView.getWidth(),_appView.getHeight());
//            _appView.transform = CGAffineTransformScale(CGAffineTransformMakeTranslation(dx, dy), appScale,appScale);
//            _appView.alpha = 1;
//            _appView.maskView = _appLaunchMaskView;
//
//            _appLaunchMaskView.transform = CGAffineTransformMakeScale(0.01, 0.01);
//
//            double springboardScale = Math.min(getWidth(),getHeight())/(60*item.getScale());
//
//            double maskScale = Math.max(getWidth(),getHeight())/(60*item.getScale())*1.2*item.getScale();
//
//            [UIView animateWithDuration:0.5 animations:^{
//            _appView.transform = CGAffineTransformIdentity;
//            _appView.alpha = 1;
//
//            _appLaunchMaskView.transform = CGAffineTransformMakeScale(maskScale,maskScale);
//
//            _springboard.transform = CGAffineTransformTranslate(CGAffineTransformMakeScale(springboardScale,springboardScale), -dx, -dy);
//            _springboard.alpha = 0;
//        } completion:^(BOOL finished) {
//            _appView.maskView = nil;
//            _appLaunchMaskView.transform = CGAffineTransformIdentity;
//
//            _springboard.transform = CGAffineTransformIdentity;
//            _springboard.alpha = 1;
//            NSUInteger index = [_springboard indexOfItemClosestToPoint:[_springboard convertPoint:pointInSelf fromView:self]];
//            [_springboard centerOnIndex:index zoomScale:_springboard.zoomScale animated:NO];

//            [[LMAppController sharedInstance] openAppWithBundleIdentifier:item.bundleIdentifier];
//        }];
        }
    }

    public void quitApp() {
        if (_isAppLaunched) {
            _isAppLaunched = false;

//            CGPoint pointInSelf = [self convertPoint:_lastLaunchedItem.icon.center fromView:_lastLaunchedItem];
//            float dx = pointInSelf.x-_appView.center.x;
//            float dy = pointInSelf.y-_appView.center.y;
//
//            double appScale = 60*_lastLaunchedItem.scale/Math.min(_appView.getWidth(),_appView.getHeight());
//
//            CGAffineTransform appTransform = CGAffineTransformScale(CGAffineTransformMakeTranslation(dx, dy), appScale, appScale);
//            _appView.maskView = _appLaunchMaskView;
//
//            double springboardScale = Math.min(self.bounds.size.width,self.bounds.size.height)/(60*_lastLaunchedItem.scale);
//            _springboard.transform = CGAffineTransformTranslate(CGAffineTransformMakeScale(springboardScale,springboardScale), -dx, -dy);
//            _springboard.alpha = 0;
//
//            double maskScale = Math.max(self.bounds.size.width,self.bounds.size.height)/(60*_lastLaunchedItem.scale)*1.2*_lastLaunchedItem.scale;
//
//            _appLaunchMaskView.transform = CGAffineTransformMakeScale(maskScale,maskScale);
//
//            [UIView animateWithDuration:0.5 delay:0 options:UIViewAnimationOptionCurveEaseInOut animations:^{
//            _appView.alpha = 1;
//            _appView.transform = appTransform;
//
//            _appLaunchMaskView.transform = CGAffineTransformMakeScale(0.01, 0.01);
//
//            _springboard.alpha = 1;
//            _springboard.transform = CGAffineTransformIdentity;
//        } completion:^(BOOL finished) {
//            _appView.alpha = 0;
//            _appView.maskView = nil;
//        }];

            _lastLaunchedItem = null;
        }
    }

//    #pragma mark - UIView

//    - (instancetype)initWithCoder:(NSCoder *)aDecoder
//    {
//        self = [super initWithCoder:aDecoder];
//        if(self)
//        {
//            Rect fullFrame = RectMake(0, 0, self.frame.size.width, self.frame.size.height);
//            UIViewAutoresizing mask = UIViewAutoresizingFlexibleHeight | UIViewAutoresizingFlexibleWidth;
//
//            UIImageView* bg = [[UIImageView alloc] initWithFrame:fullFrame];
//            bg.image = [UIImage imageNamed:@"Wallpaper.png"];
//            bg.contentMode = UIViewContentModeScaleAspectFill;
//            bg.autoresizingMask = mask;
//            [self addSubview:bg];
//
//            _springboard = [[LMSpringboardView alloc] initWithFrame:fullFrame];
//            _springboard.autoresizingMask = mask;
//
//            NSMutableArray* itemViews = [NSMutableArray array];
//
//            NSArray* apps = [LMAppController sharedInstance].installedApplications;
//
//            // pre-render the known icons
//            NSMutableArray* images = [NSMutableArray array];
//            UIBezierPath* clipPath = [UIBezierPath bezierPathWithOvalInRect:RectInset(RectMake(0, 0, 60, 60), 0.5,0.5)];
//            for(LMApp* app in apps)
//            {
//                UIImage* image = app.icon;
//
//                UIGraphicsBeginImageContextWithOptions(CGSizeMake(60, 60), NO, [UIScreen mainScreen].scale);
//                [clipPath addClip];
//                [image drawInRect:RectMake(0, 0, 60, 60)];
//                UIImage* renderedImage = UIGraphicsGetImageFromCurrentImageContext();
//                UIGraphicsEndImageContext();
//
//                [images addObject:renderedImage];
//            }
//
//            // build out item set
//            NSInteger index = 0;
//            for(LMApp* app in apps)
//            {
//                WatchSpringboardItemView* item = [[WatchSpringboardItemView alloc] init];
//                item.bundleIdentifier = app.bundleIdentifier;
//                [item setTitle:app.name];
//                item.icon.image = images[index++];
//                [itemViews addObject:item];
//            }
//            _springboard.itemViews = itemViews;
//
//            [self addSubview:_springboard];
//
//            _appView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"App.png"]];
//            _appView.transform = CGAffineTransformMakeScale(0, 0);
//            _appView.alpha = 0;
//            _appView.backgroundColor = [UIColor whiteColor];
//            [self addSubview:_appView];
//
//            _appLaunchMaskView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"Icon.png"]];
//
//            _respringButton = [UIButton buttonWithType:UIButtonTypeCustom];
//            [self addSubview:_respringButton];
//        }
//        return self;
//    }

    public void layoutSubviews() {
//        [super layoutSubviews];

//        Rect statusFrame = {0};
//        if(self.window != nil)
//        {
//            Rect statusFrame = [UIApplication sharedApplication].statusBarFrame;
//            statusFrame = [self.window convertRect:statusFrame toView:self];
//
//            UIEdgeInsets insets = _springboard.contentInset;
//            insets.top = statusFrame.size.height;
//            _springboard.contentInset = insets;
//        }
//
//        Size size = getSize();
//
//        _appView.set.bounds = RectMake(0, 0, size.width, size.height);
//        _appView.center = CGPointMake(size.width*0.5, size.height*0.5);
//
//        _appLaunchMaskView.center  =CGPointMake(size.width*0.5, size.height*0.5+statusFrame.size.height);
//
//        _respringButton.bounds = RectMake(0, 0, 60, 60);
//        _respringButton.center = CGPointMake(size.width*0.5, size.height-60*0.5);
    }

    private Size getSize() {
        return new Size(getWidth(), getHeight());
    }

}
