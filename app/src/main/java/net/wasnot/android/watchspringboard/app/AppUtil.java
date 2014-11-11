package net.wasnot.android.watchspringboard.app;

import java.util.List;

/**
 * Created by akihiroaida on 2014/10/30.
 */
public class AppUtil {

   List installedApplications;
    PrivateApi_LSApplicationWorkspace _workspace;
    List _installedApplications;


   private AppUtil()
    {
//            _workspace = [NSClassFromString(@"LSApplicationWorkspace") new];


//        return self;
    }

//    - (NSArray*)readApplications
//    {
//        NSArray* allInstalledApplications = [_workspace allInstalledApplications];
//        NSMutableArray* applications = [NSMutableArray arrayWithCapacity:allInstalledApplications.count];
//        for(id proxy in allInstalledApplications)
//        {
//            LMApp* app = [LMApp appWithPrivateProxy:proxy];
//            if(!app.isHiddenApp)
//            {
//                [applications addObject:app];
//            }
//        }
//
//        return applications;
//    }
//
//   public void installedApplications()
//    {
//        if(nil == _installedApplications)
//        {
//            _installedApplications = [self readApplications];
//        }
//
//        return _installedApplications;
//    }
//
//    public boolean openAppWithBundleIdentifier:(NSString *)bundleIdentifier
//    {
//        return (BOOL)[_workspace openApplicationWithBundleID:bundleIdentifier];
//    }
//
//    public static AppUtil sharedInstance()
//    {
//        static dispatch_once_t once;
//        static id sharedInstance;
//        dispatch_once(&once, ^{
//        sharedInstance = [[self alloc] init];
//    });
//        return sharedInstance;
//    }


    interface PrivateApi_LSApplicationWorkspace{
        public List allInstalledApplications();
        public boolean openApplicationWithBundleID(String id);
    }


}
