/*
 * Copyright (c) 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.Salt.SaltAPI.Entities;

import net.dv8tion.jda.core.entities.User;

import java.time.ZonedDateTime;

public class RoadItem {
    private User author;
    private ZonedDateTime setDate;
    private ZonedDateTime endDate;
    private String title;
    private String description;

    public RoadItem(User author, ZonedDateTime setDate, ZonedDateTime endDate, String title, String description) {
        this.author = author;
        this.setDate = setDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "RoadItem{" + "author=" + author.toString() + ", setDate=" + setDate + ", endDate=" + endDate + ", title='" + title + '\'' + ", description='" + description + '\'' + '}';
    }

    public User getAuthor() {
        return author;
    }

    public ZonedDateTime getSetDate() {
        return setDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
