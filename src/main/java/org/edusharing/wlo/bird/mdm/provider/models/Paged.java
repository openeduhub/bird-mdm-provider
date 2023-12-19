package org.edusharing.wlo.bird.mdm.provider.models;

import lombok.Getter;

import java.util.List;

public record Paged<T>(List<T> content, int count, int total) {
}
