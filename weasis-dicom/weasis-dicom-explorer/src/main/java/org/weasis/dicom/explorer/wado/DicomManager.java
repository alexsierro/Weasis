/*******************************************************************************
 * Copyright (c) 2010 Nicolas Roduit.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Nicolas Roduit - initial API and implementation
 ******************************************************************************/
package org.weasis.dicom.explorer.wado;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.prefs.Preferences;
import org.weasis.core.api.service.BundlePreferences;
import org.weasis.dicom.codec.TransferSyntax;

public class DicomManager {

    /** The single instance of this singleton class. */

    private static DicomManager instance;
    private TransferSyntax wadoTSUID;
    private boolean portableDirCache;

    /**
     * Return the single instance of this class. This method guarantees the singleton property of this class.
     */
    public static synchronized DicomManager getInstance() {
        if (instance == null) {
            instance = new DicomManager();
        }
        return instance;
    }

    private DicomManager() {
        portableDirCache = true;
        restoreDefaultValues();
        if ("superuser".equals(System.getProperty("weasis.user.prefs"))) { //$NON-NLS-1$ //$NON-NLS-2$
            final BundleContext context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
            Preferences pref = BundlePreferences.getDefaultPreferences(context);
            if (pref != null) {
                Preferences prefNode = pref.node("wado"); //$NON-NLS-1$
                wadoTSUID = TransferSyntax.getTransferSyntax(prefNode.get("compression.type", "NONE")); //$NON-NLS-1$ //$NON-NLS-2$
                if (wadoTSUID.getCompression() != null) {
                    wadoTSUID.setCompression(prefNode.getInt("compression.rate", 75)); //$NON-NLS-1$
                }
            }
        }
    }

    public boolean isPortableDirCache() {
        return portableDirCache;
    }

    public void setPortableDirCache(boolean portableDirCache) {
        this.portableDirCache = portableDirCache;
    }

    public TransferSyntax getWadoTSUID() {
        return wadoTSUID;
    }

    public void setWadoTSUID(TransferSyntax wadoTSUID) {
        this.wadoTSUID = wadoTSUID == null ? TransferSyntax.NONE : wadoTSUID;
    }

    public void restoreDefaultValues() {
        this.wadoTSUID = TransferSyntax.NONE;
    }

    public void savePreferences() {
        if ("superuser".equals(System.getProperty("weasis.user.prefs"))) { //$NON-NLS-1$ //$NON-NLS-2$
            final BundleContext context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
            Preferences prefs = BundlePreferences.getDefaultPreferences(context);
            if (prefs != null) {
                Preferences prefNode = prefs.node("wado"); //$NON-NLS-1$
                BundlePreferences.putStringPreferences(prefNode, "compression.type", wadoTSUID.name()); //$NON-NLS-1$
                if (wadoTSUID.getCompression() != null) {
                    BundlePreferences.putIntPreferences(prefNode, "compression.rate", wadoTSUID.getCompression()); //$NON-NLS-1$
                }
            }
        }
    }
}
