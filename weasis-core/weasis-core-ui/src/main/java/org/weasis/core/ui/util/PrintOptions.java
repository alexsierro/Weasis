/*******************************************************************************
 * Copyright (c) 2011 Weasis Team.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Nicolas Roduit, Marcelo Porto  - initial API and implementation
 ******************************************************************************/
package org.weasis.core.ui.util;

import org.weasis.core.ui.Messages;

/**
 * 
 * @author Marcelo Porto (marcelo@animati.com.br)
 * @version 18/11/2011
 */
public class PrintOptions {
    public enum SCALE {
        ShrinkToPage {
            @Override
            public String toString() {
                return Messages.getString("PrintOptions.shrink"); //$NON-NLS-1$
            }
        },
        FitToPage {
            @Override
            public String toString() {
                return Messages.getString("PrintOptions.fit"); //$NON-NLS-1$
            }
        },
        Custom {
            @Override
            public String toString() {
                return Messages.getString("PrintOptions.custom"); //$NON-NLS-1$
            }
        }
    }

    private Boolean hasAnnotations;
    private double imageScale;
    private boolean center;
    private boolean color;
    private SCALE scale;

    public PrintOptions(Boolean hasAnnotations, double imageScale) {
        this.hasAnnotations = hasAnnotations;
        this.imageScale = imageScale;
        this.center = true;
        this.scale = SCALE.ShrinkToPage;
        this.color = true;
    }

    public SCALE getScale() {
        return scale;
    }

    public void setScale(SCALE scale) {
        this.scale = scale;
    }

    public Boolean getHasAnnotations() {
        return hasAnnotations;
    }

    public void setHasAnnotations(Boolean hasAnnotations) {
        this.hasAnnotations = hasAnnotations;
    }

    public double getImageScale() {
        return imageScale;
    }

    public void setImageScale(double imageScale) {
        this.imageScale = imageScale;
    }

    public boolean isCenter() {
        return center;
    }

    public void setCenter(boolean center) {
        this.center = center;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

}
