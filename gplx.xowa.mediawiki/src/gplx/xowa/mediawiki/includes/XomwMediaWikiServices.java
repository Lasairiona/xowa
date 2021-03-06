/*
XOWA: the XOWA Offline Wiki Application
Copyright (C) 2012-2017 gnosygnu@gmail.com

XOWA is licensed under the terms of the General Public License (GPL) Version 3,
or alternatively under the terms of the Apache License Version 2.0.

You may use XOWA according to either of these licenses as is most appropriate
for your project on a case-by-case basis.

The terms of each license can be found in the source code repository:

GPLv3 License: https://github.com/gnosygnu/xowa/blob/master/LICENSE-GPLv3.txt
Apache License: https://github.com/gnosygnu/xowa/blob/master/LICENSE-APACHE2.txt
*/
package gplx.xowa.mediawiki.includes; import gplx.*; import gplx.xowa.*; import gplx.xowa.mediawiki.*;
import gplx.xowa.mediawiki.languages.*;
import gplx.xowa.mediawiki.includes.title.*;
/**
* MediaWikiServices is the service locator for the application scope of MediaWiki.
* Its implemented as a simple configurable DI container.
* MediaWikiServices acts as a top level factory/registry for top level services, and builds
* the network of service objects that defines MediaWiki's application logic.
* It acts as an entry point to MediaWiki's dependency injection mechanism.
*
* Services are defined in the "wiring" array passed to the constructor,
* or by calling defineService().
*
* @see docs/injection.txt for an overview of using dependency injection in the
*      MediaWiki code super.
*/
public class XomwMediaWikiServices {
	// XO.MW.SKIP:remove global getInstance(). See XomwEnv
	private final    XomwMediaWikiTitleCodec titleParser;

	public XomwMediaWikiServices(XomwLanguage language) {
		this.titleParser = new XomwMediaWikiTitleCodec(language);
	}

//		/**
//		* Replaces the global MediaWikiServices instance.
//		*
//		* @since 1.28
//		*
//		* @note This is for use in PHPUnit tests only!
//		*
//		* @throws MWException if called outside of PHPUnit tests.
//		*
//		* @param MediaWikiServices $services The new MediaWikiServices Object.
//		*
//		* @return MediaWikiServices The old MediaWikiServices Object, so it can be restored later.
//		*/
//		public static function forceGlobalInstance( MediaWikiServices $services ) {
//			if ( !defined( 'MW_PHPUNIT_TEST' ) ) {
//				throw new MWException( __METHOD__ . ' must not be used outside unit tests.' );
//			}
//
//			$old = self::getInstance();
//			self::$instance = $services;
//
//			return $old;
//		}
//
//		/**
//		* Creates a new instance of MediaWikiServices and sets it as the global default
//		* instance. getInstance() will return a different MediaWikiServices Object
//		* after every call to resetGlobalInstance().
//		*
//		* @since 1.28
//		*
//		* @warning This should not be used during normal operation. It is intended for use
//		* when the configuration has changed significantly since bootstrap time, e.g.
//		* during the installation process or during testing.
//		*
//		* @warning Calling resetGlobalInstance() may leave the application in an inconsistent
//		* state. Calling this is only safe under the ASSUMPTION that NO REFERENCE to
//		* any of the services managed by MediaWikiServices exist. If any service objects
//		* managed by the old MediaWikiServices instance remain in use, they may INTERFERE
//		* with the operation of the services managed by the new MediaWikiServices.
//		* Operating with a mix of services created by the old and the new
//		* MediaWikiServices instance may lead to INCONSISTENCIES and even DATA LOSS!
//		* Any class implementing LAZY LOADING is especially prone to this problem,
//		* since instances would typically retain a reference to a storage layer service.
//		*
//		* @see forceGlobalInstance()
//		* @see resetGlobalInstance()
//		* @see resetBetweenTest()
//		*
//		* @param Config|null $bootstrapConfig The Config Object to be registered as the
//		*        'BootstrapConfig' service. This has to contain at least the information
//		*        needed to set up the 'ConfigFactory' service. If not given, the bootstrap
//		*        config of the old instance of MediaWikiServices will be re-used. If there
//		*        was no previous instance, a new GlobalVarConfig Object will be used to
//		*        bootstrap the services.
//		*
//		* @param String $quick Set this to "quick" to allow expensive resources to be re-used.
//		* See SalvageableService for details.
//		*
//		* @throws MWException If called after MW_SERVICE_BOOTSTRAP_COMPLETE has been defined in
//		*         Setup.php (unless MW_PHPUNIT_TEST or MEDIAWIKI_INSTALL or RUN_MAINTENANCE_IF_MAIN
//		*          is defined).
//		*/
//		public static function resetGlobalInstance( Config $bootstrapConfig = null, $quick = '' ) {
//			if ( self::$instance === null ) {
//				// no global instance yet, nothing to reset
//				return;
//			}
//
//			self::failIfResetNotAllowed( __METHOD__ );
//
//			if ( $bootstrapConfig === null ) {
//				$bootstrapConfig = self::$instance->getBootstrapConfig();
//			}
//
//			$oldInstance = self::$instance;
//
//			self::$instance = self::newInstance( $bootstrapConfig, 'load' );
//			self::$instance->importWiring( $oldInstance, [ 'BootstrapConfig' ] );
//
//			if ( $quick === 'quick' ) {
//				self::$instance->salvage( $oldInstance );
//			} else {
//				$oldInstance->destroy();
//			}
//		}
//
//		/**
//		* Salvages the state of any salvageable service instances in $other.
//		*
//		* @note $other will have been destroyed when salvage() returns.
//		*
//		* @param MediaWikiServices $other
//		*/
//		private function salvage( self $other ) {
//			foreach ( $this->getServiceNames() as $name ) {
//				// The service could be new in the new instance and not registered in the
//				// other instance (e.g. an extension that was loaded after the instantiation of
//				// the other instance. Skip this service in this case. See T143974
//				try {
//					$oldService = $other->peekService( $name );
//				} catch ( NoSuchServiceException $e ) {
//					continue;
//				}
//
//				if ( $oldService instanceof SalvageableService ) {
//					/** @var SalvageableService $newService */
//					$newService = $this->getService( $name );
//					$newService->salvage( $oldService );
//				}
//			}
//
//			$other->destroy();
//		}
//
//		/**
//		* Creates a new MediaWikiServices instance and initializes it according to the
//		* given $bootstrapConfig. In particular, all wiring files defined in the
//		* ServiceWiringFiles setting are loaded, and the MediaWikiServices hook is called.
//		*
//		* @param Config|null $bootstrapConfig The Config Object to be registered as the
//		*        'BootstrapConfig' service.
//		*
//		* @param String $loadWiring set this to 'load' to load the wiring files specified
//		*        in the 'ServiceWiringFiles' setting in $bootstrapConfig.
//		*
//		* @return MediaWikiServices
//		* @throws MWException
//		* @throws \FatalError
//		*/
//		private static function newInstance( Config $bootstrapConfig, $loadWiring = '' ) {
//			$instance = new self( $bootstrapConfig );
//
//			// Load the default wiring from the specified files.
//			if ( $loadWiring === 'load' ) {
//				$wiringFiles = $bootstrapConfig->get( 'ServiceWiringFiles' );
//				$instance->loadWiringFiles( $wiringFiles );
//			}
//
//			// Provide a traditional hook point to allow extensions to configure services.
//			Hooks::run( 'MediaWikiServices', [ $instance ] );
//
//			return $instance;
//		}
//
//		/**
//		* Disables all storage layer services. After calling this, any attempt to access the
//		* storage layer will result in an error. Use resetGlobalInstance() to restore normal
//		* operation.
//		*
//		* @since 1.28
//		*
//		* @warning This is intended for extreme situations only and should never be used
//		* while serving normal web requests. Legitimate use cases for this method include
//		* the installation process. Test fixtures may also use this, if the fixture relies
//		* on globalState.
//		*
//		* @see resetGlobalInstance()
//		* @see resetChildProcessServices()
//		*/
//		public static function disableStorageBackend() {
//			// TODO: also disable some Caches, JobQueues, etc
//			$destroy = [ 'DBLoadBalancer', 'DBLoadBalancerFactory' ];
//			$services = self::getInstance();
//
//			foreach ( $destroy as $name ) {
//				$services->disableService( $name );
//			}
//
//			ObjectCache::clear();
//		}
//
//		/**
//		* Resets any services that may have become stale after a child process
//		* returns from after pcntl_fork(). It's also safe, but generally unnecessary,
//		* to call this method from the parent process.
//		*
//		* @since 1.28
//		*
//		* @note This is intended for use in the context of process forking only!
//		*
//		* @see resetGlobalInstance()
//		* @see disableStorageBackend()
//		*/
//		public static function resetChildProcessServices() {
//			// NOTE: for now, just reset everything. Since we don't know the interdependencies
//			// between services, we can't do this more selectively at this time.
//			self::resetGlobalInstance();
//
//			// Child, reseed because there is no bug in PHP:
//			// https://bugs.php.net/bug.php?id=42465
//			mt_srand( getmypid() );
//		}
//
//		/**
//		* Resets the given service for testing purposes.
//		*
//		* @since 1.28
//		*
//		* @warning This is generally unsafe! Other services may still retain references
//		* to the stale service instance, leading to failures and inconsistencies. Subclasses
//		* may use this method to reset specific services under specific instances, but
//		* it should not be exposed to application logic.
//		*
//		* @note With proper dependency injection used throughout the codebase, this method
//		* should not be needed. It is provided to allow tests that pollute global service
//		* instances to clean up.
//		*
//		* @param String $name
//		* @param boolean $destroy Whether the service instance should be destroyed if it exists.
//		*        When set to false, any existing service instance will effectively be detached
//		*        from the container.
//		*
//		* @throws MWException if called outside of PHPUnit tests.
//		*/
//		public function resetServiceForTesting( $name, $destroy = true ) {
//			if ( !defined( 'MW_PHPUNIT_TEST' ) && !defined( 'MW_PARSER_TEST' ) ) {
//				throw new MWException( 'resetServiceForTesting() must not be used outside unit tests.' );
//			}
//
//			$this->resetService( $name, $destroy );
//		}
//
//		/**
//		* Convenience method that throws an exception unless it is called during a phase in which
//		* resetting of global services is allowed. In general, services should not be reset
//		* individually, since that may introduce inconsistencies.
//		*
//		* @since 1.28
//		*
//		* This method will throw an exception if:
//		*
//		* - self::$resetInProgress is false (to allow all services to be reset together
//		*   via resetGlobalInstance)
//		* - and MEDIAWIKI_INSTALL is not defined (to allow services to be reset during installation)
//		* - and MW_PHPUNIT_TEST is not defined (to allow services to be reset during testing)
//		*
//		* This method is intended to be used to safeguard against accidentally resetting
//		* global service instances that are not yet managed by MediaWikiServices. It is
//		* defined here in the MediaWikiServices services class to have a central place
//		* for managing service bootstrapping and resetting.
//		*
//		* @param String $method the name of the caller method, as given by __METHOD__.
//		*
//		* @throws MWException if called outside bootstrap mode.
//		*
//		* @see resetGlobalInstance()
//		* @see forceGlobalInstance()
//		* @see disableStorageBackend()
//		*/
//		public static function failIfResetNotAllowed( $method ) {
//			if ( !defined( 'MW_PHPUNIT_TEST' )
//				&& !defined( 'MW_PARSER_TEST' )
//				&& !defined( 'MEDIAWIKI_INSTALL' )
//				&& !defined( 'RUN_MAINTENANCE_IF_MAIN' )
//				&& defined( 'MW_SERVICE_BOOTSTRAP_COMPLETE' )
//			) {
//				throw new MWException( $method . ' may only be called during bootstrapping and unit tests!' );
//			}
//		}
//
//		/**
//		* @param Config $config The Config Object to be registered as the 'BootstrapConfig' service.
//		*        This has to contain at least the information needed to set up the 'ConfigFactory'
//		*        service.
//		*/
//		public function __construct( Config $config ) {
//			parent::__construct();
//
//			// Register the given Config Object as the bootstrap config service.
//			$this->defineService( 'BootstrapConfig', function() use ( $config ) {
//				return $config;
//			} );
//		}
//
//		// CONVENIENCE GETTERS ////////////////////////////////////////////////////
//
//		/**
//		* Returns the Config Object containing the bootstrap configuration.
//		* Bootstrap configuration would typically include database credentials
//		* and other information that may be needed before the ConfigFactory
//		* service can be instantiated.
//		*
//		* @note This should only be used during bootstrapping, in particular
//		* when creating the MainConfig service. Application logic should
//		* use getMainConfig() to get a Config instances.
//		*
//		* @since 1.27
//		* @return Config
//		*/
//		public function getBootstrapConfig() {
//			return $this->getService( 'BootstrapConfig' );
//		}
//
//		/**
//		* @since 1.27
//		* @return ConfigFactory
//		*/
//		public function getConfigFactory() {
//			return $this->getService( 'ConfigFactory' );
//		}
//
//		/**
//		* Returns the Config Object that provides configuration for MediaWiki core.
//		* This may or may not be the same Object that is returned by getBootstrapConfig().
//		*
//		* @since 1.27
//		* @return Config
//		*/
//		public function getMainConfig() {
//			return $this->getService( 'MainConfig' );
//		}
//
//		/**
//		* @since 1.27
//		* @return SiteLookup
//		*/
//		public function getSiteLookup() {
//			return $this->getService( 'SiteLookup' );
//		}
//
//		/**
//		* @since 1.27
//		* @return SiteStore
//		*/
//		public function getSiteStore() {
//			return $this->getService( 'SiteStore' );
//		}
//
//		/**
//		* @since 1.28
//		* @return InterwikiLookup
//		*/
//		public function getInterwikiLookup() {
//			return $this->getService( 'InterwikiLookup' );
//		}
//
//		/**
//		* @since 1.27
//		* @return StatsdDataFactory
//		*/
//		public function getStatsdDataFactory() {
//			return $this->getService( 'StatsdDataFactory' );
//		}
//
//		/**
//		* @since 1.27
//		* @return EventRelayerGroup
//		*/
//		public function getEventRelayerGroup() {
//			return $this->getService( 'EventRelayerGroup' );
//		}
//
//		/**
//		* @since 1.27
//		* @return SearchEngine
//		*/
//		public function newSearchEngine() {
//			// New engine Object every time, since they keep state
//			return $this->getService( 'SearchEngineFactory' )->create();
//		}
//
//		/**
//		* @since 1.27
//		* @return SearchEngineFactory
//		*/
//		public function getSearchEngineFactory() {
//			return $this->getService( 'SearchEngineFactory' );
//		}
//
//		/**
//		* @since 1.27
//		* @return SearchEngineConfig
//		*/
//		public function getSearchEngineConfig() {
//			return $this->getService( 'SearchEngineConfig' );
//		}
//
//		/**
//		* @since 1.27
//		* @return SkinFactory
//		*/
//		public function getSkinFactory() {
//			return $this->getService( 'SkinFactory' );
//		}
//
//		/**
//		* @since 1.28
//		* @return LBFactory
//		*/
//		public function getDBLoadBalancerFactory() {
//			return $this->getService( 'DBLoadBalancerFactory' );
//		}
//
//		/**
//		* @since 1.28
//		* @return LoadBalancer The main DB load balancer for the local wiki.
//		*/
//		public function getDBLoadBalancer() {
//			return $this->getService( 'DBLoadBalancer' );
//		}
//
//		/**
//		* @since 1.28
//		* @return WatchedItemStore
//		*/
//		public function getWatchedItemStore() {
//			return $this->getService( 'WatchedItemStore' );
//		}
//
//		/**
//		* @since 1.28
//		* @return WatchedItemQueryService
//		*/
//		public function getWatchedItemQueryService() {
//			return $this->getService( 'WatchedItemQueryService' );
//		}
//
//		/**
//		* @since 1.28
//		* @return CryptRand
//		*/
//		public function getCryptRand() {
//			return $this->getService( 'CryptRand' );
//		}
//
//		/**
//		* @since 1.28
//		* @return CryptHKDF
//		*/
//		public function getCryptHKDF() {
//			return $this->getService( 'CryptHKDF' );
//		}
//
//		/**
//		* @since 1.28
//		* @return MediaHandlerFactory
//		*/
//		public function getMediaHandlerFactory() {
//			return $this->getService( 'MediaHandlerFactory' );
//		}
//
//		/**
//		* @since 1.28
//		* @return MimeAnalyzer
//		*/
//		public function getMimeAnalyzer() {
//			return $this->getService( 'MimeAnalyzer' );
//		}
//
//		/**
//		* @since 1.28
//		* @return ProxyLookup
//		*/
//		public function getProxyLookup() {
//			return $this->getService( 'ProxyLookup' );
//		}
//
//		/**
//		* @since 1.29
//		* @return Parser
//		*/
//		public function getParser() {
//			return $this->getService( 'Parser' );
//		}
//
//		/**
//		* @since 1.28
//		* @return GenderCache
//		*/
//		public function getGenderCache() {
//			return $this->getService( 'GenderCache' );
//		}
//
//		/**
//		* @since 1.28
//		* @return LinkCache
//		*/
//		public function getLinkCache() {
//			return $this->getService( 'LinkCache' );
//		}
//
//		/**
//		* @since 1.28
//		* @return LinkRendererFactory
//		*/
//		public function getLinkRendererFactory() {
//			return $this->getService( 'LinkRendererFactory' );
//		}
//
//		/**
//		* LinkRenderer instance that can be used
//		* if no custom options are needed
//		*
//		* @since 1.28
//		* @return LinkRenderer
//		*/
//		public function getLinkRenderer() {
//			return $this->getService( 'LinkRenderer' );
//		}

	/**
	* @since 1.28
	* @return TitleFormatter
	*/
	public XomwMediaWikiTitleCodec getTitleFormatter() {
		// return $this->getService( 'TitleFormatter' );
		return titleParser;
	}

	/**
	* @since 1.28
	* @return TitleParser
	*/
	public XomwMediaWikiTitleCodec getTitleParser() {
		// return $this->getService( 'TitleParser' );
		return titleParser;
	}

//		/**
//		* @since 1.28
//		* @return \BagOStuff
//		*/
//		public function getMainObjectStash() {
//			return $this->getService( 'MainObjectStash' );
//		}
//
//		/**
//		* @since 1.28
//		* @return \WANObjectCache
//		*/
//		public function getMainWANObjectCache() {
//			return $this->getService( 'MainWANObjectCache' );
//		}
//
//		/**
//		* @since 1.28
//		* @return \BagOStuff
//		*/
//		public function getLocalServerObjectCache() {
//			return $this->getService( 'LocalServerObjectCache' );
//		}
//
//		/**
//		* @since 1.28
//		* @return VirtualRESTServiceClient
//		*/
//		public function getVirtualRESTServiceClient() {
//			return $this->getService( 'VirtualRESTServiceClient' );
//		}
//
//		///////////////////////////////////////////////////////////////////////////
//		// NOTE: When adding a service getter here, don't forget to add a test
//		// case for it in MediaWikiServicesTest::provideGetters() and in
//		// MediaWikiServicesTest::provideGetService()!
//		///////////////////////////////////////////////////////////////////////////
}
