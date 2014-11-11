package net.wasnot.android.watchspringboard.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by akihiroaida on 2014/10/29.
 */
public class WatchSpringboardItemView extends View {

    public WatchSpringboardItemView(Context context) {
        super(context);
        init();
    }

    public WatchSpringboardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WatchSpringboardItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private static final double kLMSpringboardItemViewSmallThreshold = 0.75;

    View _visualEffectView;
    ImageView _visualEffectMaskView;
//    #pragma mark -

    public ImageView _icon;
    public TextView _label;
    public float _scale;
    public boolean _isFolderLike;
    public String _bundleIdentifier;

    public void init() {
//        self =[super init];
//        if (self) {
        _scale = 1;
        _label = new TextView(getContext());
//        _label.opaque = NO;
        _label.setAlpha(0);
//        _label.setBackgroundColor(Color.TRANSPARENT);
        _label.setTextColor(Color.WHITE);
//        _label.font =[UIFont systemFontOfSize:[UIFont smallSystemFontSize]];
        _label.setTextSize(10);
//        [self addSubview:_label];
        _icon = new ImageView(getContext());
//        [self addSubview:_icon];
//        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int w = getWidth();
        int h = getHeight();
        //self.bounds.size;
//        _icon.center = CGPointMake(size.width * 0.5, size.height * 0.5);
//        _icon.bounds = CGRectMake(0, 0, size.width, size.height);
        _icon.setTop(0);
        _icon.setLeft(0);
        _icon.setRight(w);
        _icon.setBottom(h);
//        _visualEffectView.center = _icon.center;
//        _visualEffectView.bounds = _icon.bounds;
//        _visualEffectMaskView.center = _icon.center;
//        _visualEffectMaskView.bounds = _icon.bounds;
//        [_label sizeToFit];
//        _label.center = CGPointMake(size.width * 0.5, size.height + 4);
        _label.setTop(0);
        _label.setLeft(0);
        _label.setRight(w);
        _label.setBottom(h);
        float scale = 60 / w;
//        _icon.transform = CGAffineTransformMakeScale(scale, scale);
//        _visualEffectView.transform = _icon.transform;

        _icon.draw(canvas);
        _label.draw(canvas);
    }

    public void setScale(float scale) {
        setScale(scale, false);
//        [self setScale:scale animated:NO];
    }

    public void setTitle(String title) {
        _label.setText(title);
//        [self setNeedsLayout];
        this.requestLayout();
    }

    public void setIsFolderLike(boolean isFolderLike) {
        if (_isFolderLike != isFolderLike) {
            _isFolderLike = isFolderLike;
//            if (_isFolderLike) {
//                _visualEffectView = new View(getContext());
//                View vev =[[UIVisualEffectView alloc]initWithEffect:[UIBlurEffect effectWithStyle:
//                UIBlurEffectStyleLight]];
//                vev.autoresizingMask = UIViewAutoresizingFlexibleHeight
//                        | UIViewAutoresizingFlexibleWidth;
//                [_visualEffectView addSubview:vev];
//                [self insertSubview:_visualEffectView atIndex:0];
////                _visualEffectMaskView =[[ImageView alloc]initWithImage:[UIImage imageNamed:@
////                "Icon.png"]];
//                _visualEffectMaskView = new ImageView(getContext());
//                _visualEffectMaskView.setImageResource(R.drawable.ic_launcher);
//                _visualEffectMaskView.contentMode = UIViewContentModeScaleAspectFit;
//                _visualEffectMaskView.autoresizingMask = vev.autoresizingMask;
//                _visualEffectView.maskView = _visualEffectMaskView;
//            } else {
//                [_visualEffectView removeFromSuperview];
//                _visualEffectView = null;
//                _visualEffectMaskView = null;
//            }
        }
    }

    public void setScale(float scale, boolean animated) {
        if (_scale != scale) {
            boolean wasSmallBefore = (_scale < kLMSpringboardItemViewSmallThreshold);
            _scale = scale;
//            [self setNeedsLayout];
            requestLayout();
            if ((_scale < kLMSpringboardItemViewSmallThreshold) != wasSmallBefore) {
                if (animated) {

                    float end;
                    if (_scale < kLMSpringboardItemViewSmallThreshold) {
                        end = 0;
                    } else {
                        end = 1;
                    }
                    // alphaプロパティを0fから1fに変化させます
                    ObjectAnimator objectAnimator = ObjectAnimator
                            .ofFloat(this, "alpha", getAlpha(), end);
                    // 3秒かけて実行させます
                    objectAnimator.setDuration(300);
                    // アニメーションを開始します
                    objectAnimator.start();

//                    View animateWithDuration(0.3, animations:^{
////                    [self layoutIfNeeded];
//                            requestLayout();
//                            if (_scale < kLMSpringboardItemViewSmallThreshold) {
//                                _label.setAlpha(0);
//                            } else {
//                                _label.setAlpha(1);
//                            }
//                        }
//                }];
                } else {
                    if (_scale < kLMSpringboardItemViewSmallThreshold) {
                        _label.setAlpha(0);
                    } else {
                        _label.setAlpha(1);
                    }
                }
            }
        }
    }

    public float getScale() {
        return _scale;
    }
}
