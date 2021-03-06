// Copyright (c) 2003-2013, Jodd Team (jodd.org). All Rights Reserved.

package jodd.petite;

import jodd.Jodd;
import jodd.petite.scope.Scope;
import jodd.petite.scope.DefaultScope;
import jodd.petite.scope.SingletonScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Petite configuration.
 */
public class PetiteConfig {

	private static final Logger log = LoggerFactory.getLogger(PetiteConfig.class);

	public PetiteConfig() {
		defaultScope = SingletonScope.class;
		defaultWiringMode = WiringMode.STRICT;
		detectDuplicatedBeanNames = false;
		resolveReferenceParameters = true;
		useFullTypeNames = false;
		lookupReferences = new PetiteReference[] {
				PetiteReference.NAME,
		        PetiteReference.TYPE_SHORT_NAME,
				PetiteReference.TYPE_FULL_NAME
		};
		useParamo = Jodd.isProxettaLoaded();
		wireScopedProxy = false;
		detectMixedScopes = false;
	}

	protected Class<? extends Scope> defaultScope;
	/**
	 * Returns default scope type.
	 */
	public Class<? extends Scope> getDefaultScope() {
		return defaultScope;
	}
	/**
	 * Sets default scope type.
	 */
	public void setDefaultScope(Class<? extends Scope> defaultScope) {
		if (defaultScope == DefaultScope.class) {
			throw new PetiteException("Invalid default Petite scope: scope must be a concrete scope implementation.");
		}
		if (defaultScope == null) {
			throw new PetiteException("Invalid default Petite scope: null.");
		}
		this.defaultScope = defaultScope;
	}

	// ----------------------------------------------------------------

	protected WiringMode defaultWiringMode;
	/**
	 * Returns default wiring mode.
	 */
	public WiringMode getDefaultWiringMode() {
		return defaultWiringMode;
	}
	/**
	 * Specifies default wiring mode.
	 */
	public void setDefaultWiringMode(WiringMode defaultWiringMode) {
		if ((defaultWiringMode == null) || (defaultWiringMode == WiringMode.DEFAULT)) {
			throw new PetiteException("Invalid default wiring mode: " + defaultWiringMode);
		}
		this.defaultWiringMode = defaultWiringMode;
	}
	/**
	 * Resolves wiring mode by checking if default and <code>null</code> values.
	 */
	protected WiringMode resolveWiringMode(WiringMode wiringMode) {
		if ((wiringMode == null) || (wiringMode == WiringMode.DEFAULT)) {
			wiringMode = defaultWiringMode;
		}
		return wiringMode;
	}


	// ----------------------------------------------------------------

	protected boolean detectDuplicatedBeanNames;
	/**
	 * Returns <code>true</code> if container detects duplicated bean names.
	 */
	public boolean getDetectDuplicatedBeanNames() {
		return detectDuplicatedBeanNames;
	}
	/**
	 * Specifies if an exception should be thrown if two beans with same exception are registered with this container.
	 */
	public void setDetectDuplicatedBeanNames(boolean detectDuplicatedBeanNames) {
		this.detectDuplicatedBeanNames = detectDuplicatedBeanNames;
	}

	// ----------------------------------------------------------------

	protected boolean resolveReferenceParameters;
	/**
	 * Returns <code>true</code> if parameter references should be resolved.
	 */
	public boolean getResolveReferenceParameters() {
		return resolveReferenceParameters;
	}
	/**
	 * Defines if reference parameters should be resolved.
	 */
	public void setResolveReferenceParameters(boolean resolveReferenceParameters) {
		this.resolveReferenceParameters = resolveReferenceParameters;
	}

	// ----------------------------------------------------------------

	protected boolean useFullTypeNames;

	public boolean getUseFullTypeNames() {
		return useFullTypeNames;
	}

	/**
	 * Specifies if type names should be full or short.
	 */
	public void setUseFullTypeNames(boolean useFullTypeNames) {
		this.useFullTypeNames = useFullTypeNames;
	}

	// ---------------------------------------------------------------- references

	protected PetiteReference[] lookupReferences;

	public PetiteReference[] getLookupReferences() {
		return lookupReferences;
	}

	/**
	 * Specifies references for bean name lookup, when name
	 * is not specified, in given order.
	 */
	public void setLookupReferences(PetiteReference... lookupReferences) {
		this.lookupReferences = lookupReferences;
	}

	// ----------------------------------------------------------------

	protected boolean useParamo;

	public boolean getUseParamo() {
		return useParamo;
	}

	/**
	 * Specifies if <b>Paramo</b> tool should be used to resolve
	 * method and ctor argument names. If <b>Paramo</b> is not
	 * available, this property can't be set (i.e. will be
	 * always <code>false</code>).
	 */
	public void setUseParamo(boolean useParamo) {
		if (Jodd.isProxettaLoaded() == false) {
			if (log.isWarnEnabled()) {
				log.warn("Feature not available without Proxetta");
			}
			return;
		}
		this.useParamo = useParamo;
	}

	// ----------------------------------------------------------------

	protected boolean wireScopedProxy;
	protected boolean detectMixedScopes;

	public boolean isWireScopedProxy() {
		return wireScopedProxy;
	}

	/**
	 * Defines if scoped proxies should be wired.
	 * Only available with Proxetta.
	 */
	public void setWireScopedProxy(boolean wireScopedProxy) {
		if (Jodd.isProxettaLoaded() == false) {
			if (log.isWarnEnabled()) {
				log.warn("Feature not available without Proxetta");
			}
			return;
		}
		this.wireScopedProxy = wireScopedProxy;
	}

	public boolean isDetectMixedScopes() {
		return detectMixedScopes;
	}

	/**
	 * Defines if mixed scopes should be detected as errors.
	 * If {@link #wireScopedProxy} is not set, then enabling this flag
	 * will throw an exception on mixed scopes. If {@link #wireScopedProxy} is set
	 * enabling this flag will just issue a warn message in the log.
	 * Only available with Proxetta.
	 */
	public void setDetectMixedScopes(boolean detectMixedScopes) {
		if (Jodd.isProxettaLoaded() == false) {
			if (log.isWarnEnabled()) {
				log.warn("Feature not available without Proxetta");
			}
			return;
		}
		this.detectMixedScopes = detectMixedScopes;
	}
}
