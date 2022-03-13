#import "SessionPlugin.h"
#if __has_include(<session/session-Swift.h>)
#import <session/session-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "session-Swift.h"
#endif

@implementation SessionPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftSessionPlugin registerWithRegistrar:registrar];
}
@end
