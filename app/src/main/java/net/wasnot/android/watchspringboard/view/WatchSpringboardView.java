package net.wasnot.android.watchspringboard.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * Created by akihiroaida on 2014/10/29.
 */
public class WatchSpringboardView extends ViewGroup {

    private static final String TAG = WatchSpringboardView.class.getSimpleName();
    //    @property (copy,nonatomic) NSArray* itemViews;
//    @property (nonatomic) NSUInteger itemDiameter;
    private int _itemDiameter;
    //    @property (nonatomic) NSUInteger itemPadding;
    private int _itemPadding;
//    @property (nonatomic) double minimumItemScaling;
//    @property (nonatomic) double minimumZoomLevelToLaunchApp;
//    @property (readonly) UITapGestureRecognizer* doubleTapGesture;
//    - (void)showAllContentAnimated:(BOOL)animated;
//    - (NSUInteger)indexOfItemClosestToPoint:(CGPoint)pointInSelf;
//    - (void)centerOnIndex:(NSUInteger)index zoomScale:(CGFloat)zoomScale animated:(BOOL)animated;
//    - (void)doIntroAnimation;

    //
//    UIView* _touchView;
//    UIView* _contentView;
//    //UIView* _debugRectInContent;
////UIView* _debugRectInScroll;
//// controls how much transform we apply to the views (not used)
//    float _transformFactor;
//    // a few state variables
//    NSUInteger _lastFocusedViewIndex;
//    CGFloat _zoomScaleCache;
//    CGAffineTransform _minTransform;
//    // dirty when the view changes width/height
//    BOOL _minimumZoomLevelIsDirty;
    boolean _minimumZoomLevelIsDirty;
    //    BOOL _contentSizeIsDirty;
    boolean _contentSizeIsDirty;
    //    CGSize _contentSizeUnscaled;
    SizeD _contentSizeUnscaled;
    //    CGSize _contentSizeExtra;
    SizeD _contentSizeExtra;
//    BOOL _centerOnEndDrag;
//    BOOL _centerOnEndDeccel;

    //    #define LMPointDistance(x1, y1, x2, y2) (sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2)))
    private static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    //            #define LKPointDistanceSquared(x1, y1, x2, y2) ((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2))
    private static double distanceSquared(double x1, double y1, double x2, double y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

    private GestureDetector mDetector;
    private Scroller mScroller;

    public WatchSpringboardView(Context context) {
        super(context);
        init();
    }

    public WatchSpringboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WatchSpringboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getMetrics(metrics);
        float density = metrics.density;
//        delaysContentTouches = NO;
//        showsHorizontalScrollIndicator = NO;
//        showsVerticalScrollIndicator = NO;
//        alwaysBounceHorizontal = YES;
//        alwaysBounceVertical = YES;
//        bouncesZoom = YES;
//        decelerationRate = UIScrollViewDecelerationRateFast;
//        delegate = self;
        _itemDiameter = (int) (34 * density);//68;
        _itemPadding = (int) (24 * density);//48;
//        minimumItemScaling = 0.5;
//        _transformFactor = 1;
//        _zoomScaleCache = self.zoomScale;
//        _minimumZoomLevelToLaunchApp = 0.4;
//        _touchView = [[UIView alloc] init];
////_touchView.backgroundColor = [UIColor purpleColor];
//        [self addSubview:_touchView];
//        _contentView = [[UIView alloc] init];
////_contentView.backgroundColor = [UIColor greenColor];
//        [self addSubview:_contentView];
///*_debugRectInContent = [[UIView alloc] init];
//_debugRectInContent.backgroundColor = [UIColor redColor];
//_debugRectInContent.alpha = 0.4;
//[_contentView addSubview:_debugRectInContent];
//_debugRectInScroll = [[UIView alloc] init];
//_debugRectInScroll.backgroundColor = [UIColor blueColor];
//_debugRectInScroll.alpha= 0.4;
//[self addSubview:_debugRectInScroll];*/
//        _doubleTapGesture = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(LM_didZoomGesture:)];
//        _doubleTapGesture.numberOfTapsRequired = 1;
//        [_contentView addGestureRecognizer:_doubleTapGesture];

        _contentSizeIsDirty = true;
        mScroller = new Scroller(getContext());
        mDetector = new GestureDetector(getContext(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onDown(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
                        Rect r1 = new Rect();
                        getGlobalVisibleRect(r1);
                        Rect r2 = new Rect();
                        getLocalVisibleRect(r2);

                        Log.d(TAG,
                                "onScroll " + distanceX + "," + distanceY + " " + getScrollX() + ","
                                        + getScrollY() + " " + r1 + " " + r2);

//                        if (!mScroller.isFinished()) {
//                            mScroller.abortAnimation();
//                        }
//                        mScroller.startScroll(currX, currY, (startX - currX), (startY - currY));

//                        invalidate();
                        scrollBy((int) distanceX, (int) distanceY);//★指の移動距離分スクロール。

                        return true;
                    }
                });

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "onMesure");
        if (_contentSizeIsDirty || true) {
//            // このViewGroupに指定されているレイアウトのモードを取得する
//            final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//            final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//
//            if (widthMode != MeasureSpec.EXACTLY || heightMode != MeasureSpec.EXACTLY) {
//                // レイアウトモードがEXACTLY以外のときはエラーにする
//                throw new IllegalStateException("Must measure with an exact width");
//            }
            // このViewGroupに割り当てられているサイズを取得する
            final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
            final int heightSize = MeasureSpec.getSize(heightMeasureSpec);

//            // このViewGroupのサイズをセットする
//            setMeasuredDimension(widthSize, heightSize);

            // padding分を差し引いて親の幅と高さを求める
            int width = widthSize - getPaddingLeft() - getPaddingRight();
            int height = heightSize - getPaddingTop() - getPaddingBottom();

            int childCount = getChildCount();
            int itemsPerLine = (int) Math.ceil(
                    (double) Math.min(width, height) / Math.max(width, height) * Math
                            .sqrt(childCount));
            if (itemsPerLine % 2 == 0) {
                itemsPerLine++;
            }
            int lines = (int) Math.ceil(childCount / (double) itemsPerLine);
            float newMinimumZoomScale = 0;

            int padding = _itemPadding;
//            _contentSizeUnscaled = CGSizeMake(itemsPerLine*_itemDiameter+(itemsPerLine+1)*padding+(_itemDiameter+padding)/2,
//                    lines*_itemDiameter+(2)*padding);
            _contentSizeUnscaled = new SizeD(
                    itemsPerLine * _itemDiameter + (itemsPerLine + 1) * padding
                            + (_itemDiameter + padding) / 2, lines * _itemDiameter + (2) * padding);
//            newMinimumZoomScale = MIN((size.width-insets.left-insets.right)/_contentSizeUnscaled.width, (size.height-insets.top-insets.bottom)/_contentSizeUnscaled.height);
            newMinimumZoomScale = (float) Math.min((float) (width) / _contentSizeUnscaled.width,
                    (float) (height) / _contentSizeUnscaled.height);
//            _contentSizeExtra = CGSizeMake((size.width-_itemDiameter*0.5)/newMinimumZoomScale, (size.height-_itemDiameter*0.5)/newMinimumZoomScale);
            _contentSizeExtra = new SizeD((width - _itemDiameter * 0.5) / newMinimumZoomScale,
                    (height - _itemDiameter * 0.5) / newMinimumZoomScale);
            Log.d(TAG, "size:" + width + "," + _contentSizeUnscaled.width + "," + ((float) (width)
                    / _contentSizeUnscaled.width) + " " + _contentSizeExtra);

            _contentSizeUnscaled.width += _contentSizeExtra.width;
            _contentSizeUnscaled.height += _contentSizeExtra.height;
//            _contentView.bounds = CGRectMake(0, 0, _contentSizeUnscaled.width, _contentSizeUnscaled.height);
            // このViewGroupのサイズをセットする
            setMeasuredDimension((int) _contentSizeUnscaled.width,
                    (int) _contentSizeUnscaled.height);

            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                if (i == 0) {
                    int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(width,
                            MeasureSpec.EXACTLY);
                    int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                            height, MeasureSpec.EXACTLY);
                    child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
                    continue;
                }

//                view.bounds = CGRectMake(0, 0, _itemDiameter, _itemDiameter);
                int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(_itemDiameter,
                        MeasureSpec.EXACTLY);
                int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                        _itemDiameter, MeasureSpec.EXACTLY);
                child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
            }
            _contentSizeIsDirty = true;
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d(TAG, "onLayout " + getChildCount() + " " + _itemDiameter);
// padding分を差し引いて子ビュー用の領域を求める
        int width = (r - l) - getPaddingLeft() - getPaddingRight();
        int height = (b - t) - getPaddingTop() - getPaddingBottom();

        int left = getPaddingLeft();
        int top = getPaddingTop();
        int right = left + width;
        int bottom = top + height;

        int childCount = getChildCount();
        int itemsPerLine = (int) Math.ceil(
                (double) Math.min(width, height) / Math.max(width, height) * Math.sqrt(childCount));
        if (itemsPerLine % 2 == 0) {
            itemsPerLine++;
        }
        int lines = (int) Math.ceil(childCount / (double) itemsPerLine);
        float newMinimumZoomScale = 0;
        if (_contentSizeIsDirty && false) {
            int padding = _itemPadding;
//            _contentSizeUnscaled = CGSizeMake(itemsPerLine*_itemDiameter+(itemsPerLine+1)*padding+(_itemDiameter+padding)/2,
//                    lines*_itemDiameter+(2)*padding);
            _contentSizeUnscaled = new SizeD(
                    itemsPerLine * _itemDiameter + (itemsPerLine + 1) * padding
                            + (_itemDiameter + padding) / 2, lines * _itemDiameter + (2) * padding);
//            newMinimumZoomScale = MIN((size.width-insets.left-insets.right)/_contentSizeUnscaled.width, (size.height-insets.top-insets.bottom)/_contentSizeUnscaled.height);
            newMinimumZoomScale = (float) Math.min((float) (width) / _contentSizeUnscaled.width,
                    (float) (height) / _contentSizeUnscaled.height);
//            _contentSizeExtra = CGSizeMake((size.width-_itemDiameter*0.5)/newMinimumZoomScale, (size.height-_itemDiameter*0.5)/newMinimumZoomScale);
            _contentSizeExtra = new SizeD((width - _itemDiameter * 0.5) / newMinimumZoomScale,
                    (height - _itemDiameter * 0.5) / newMinimumZoomScale);
            Log.d(TAG, "size:" + width + "," + _contentSizeUnscaled.width + "," + ((float) (width)
                    / _contentSizeUnscaled.width) + " " + _contentSizeExtra);

            _contentSizeUnscaled.width += _contentSizeExtra.width;
            _contentSizeUnscaled.height += _contentSizeExtra.height;
//            _contentView.bounds = CGRectMake(0, 0, _contentSizeUnscaled.width, _contentSizeUnscaled.height);
            setMinimumWidth((int) _contentSizeUnscaled.width);
            setMinimumHeight((int) _contentSizeUnscaled.height);
        }
        if (_minimumZoomLevelIsDirty) {
//            self.minimumZoomScale = newMinimumZoomScale;
//            CGFloat newZoom = MAX(self.zoomScale, newMinimumZoomScale);
//            if(newZoom != _zoomScaleCache || YES)
//            {
//                self.zoomScale = newZoom;
//                _zoomScaleCache = newZoom;
//                _contentView.center = CGPointMake(_contentSizeUnscaled.width*0.5*newZoom, _contentSizeUnscaled.height*0.5*newZoom);
//                self.contentSize = CGSizeMake(_contentSizeUnscaled.width*newZoom, _contentSizeUnscaled.height*newZoom);
//            }
        }
        if (_contentSizeIsDirty) {
            for (int i = 0; i < childCount; i++) {
                View view = getChildAt(i);

                if (i == 0) {
                    childLayout(view, 0, 0, width, height);
                    continue;
                }
//                view.bounds = CGRectMake(0, 0, _itemDiameter, _itemDiameter);
                int posX, posY;
                int line = i / itemsPerLine;
                int indexInLine = i % itemsPerLine;
                if (i == 0) {
// place item 0 at the center of the grid
                    line = childCount / itemsPerLine / 2;
                    indexInLine = itemsPerLine / 2;
                } else {
// switch item at center of grid to position 0
                    if (line == childCount / itemsPerLine / 2
                            && indexInLine == itemsPerLine / 2) {
                        line = 0;
                        indexInLine = 0;
                    }
                }
                int lineOffset = 0;
                if (line % 2 == 1) {
                    lineOffset = (_itemDiameter + _itemPadding) / 2;
                }
                posX = (int) (_contentSizeExtra.width * 0.5 + _itemPadding + lineOffset
                        + indexInLine * (_itemDiameter + _itemPadding) + _itemDiameter / 2f);
                posY = (int) (_contentSizeExtra.height * 0.5 + _itemPadding
                        + line * (_itemDiameter) + _itemDiameter / 2f);
//                view.center = CGPointMake(posX, posY);
                int size = (int) (_itemDiameter / 2f);
//                Log.d(TAG, i + ":" + size + " " + _itemPadding + ",");
                childLayout(view, posX - size, posY - size, posX + size, posY + size);
            }
            _contentSizeIsDirty = false;
        }
        if (_minimumZoomLevelIsDirty) {
//            if(_lastFocusedViewIndex <= [_itemViews count])
//            {
//                [self centerOnIndex:_lastFocusedViewIndex zoomScale:_zoomScaleCache animated:NO];
//            }
            _minimumZoomLevelIsDirty = false;
        }
//        _zoomScaleCache = self.zoomScale;
//        _touchView.bounds = CGRectMake(0, 0, (_contentSizeUnscaled.width-_contentSizeExtra.width)*_zoomScaleCache, (_contentSizeUnscaled.height-_contentSizeExtra.height)*_zoomScaleCache);
//        _touchView.center = CGPointMake(_contentSizeUnscaled.width*0.5*_zoomScaleCache, _contentSizeUnscaled.height*0.5*_zoomScaleCache);
//        [self LM_centerViewIfSmaller];
//        double scale = MIN(_minimumItemScaling*_transformFactor+(1-_transformFactor), 1);
//        _minTransform = CGAffineTransformMakeScale(scale, scale);
    }


    private void childLayout(View child, int l, int t, int r, int b) {
        if (child.getLayoutParams() != null && child
                .getLayoutParams() instanceof MarginLayoutParams) {
            // マージン分だけずらして配置する
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            child.layout(l + params.leftMargin, t + params.topMargin, r
                    - params.rightMargin, b - params.bottomMargin);
        } else {
            child.layout(l, t, r, b);
        }
    }

//    @Override
//    public void computeScroll() {
//        if (mScroller.computeScrollOffset()) {
//            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
//            postInvalidate();
//        }
//    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent");
        return mDetector.onTouchEvent(event);
    }

    public static class SizeD {

        public double width;
        public double height;

        public SizeD(double w, double h) {
            width = w;
            height = h;
        }

        @Override
        public String toString() {
            return "SizeD[" + width + "," + height + "]";
        }
    }
}
