/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.openmuc.jmbus.internal.cli;

public abstract class CliParameter {

    final String name;
    final String description;
    final boolean optional;
    boolean selected;

    CliParameter(CliParameterBuilder builder) {
        name = builder.name;
        description = builder.description;
        optional = builder.optional;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the optional
     */
    public boolean isOptional() {
        return optional;
    }

    public boolean isSelected() {
        return selected;
    }

    abstract int parse(String[] args, int i) throws CliParseException;

    abstract int appendSynopsis(StringBuilder sb);

    abstract void appendDescription(StringBuilder sb);

}
