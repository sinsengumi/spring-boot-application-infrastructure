package com.example.infrastructure;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
@PropertySource("classpath:git.properties")
public class GitRepositoryState {

    @Value("${git.branch}")
    private String branch;

    @Value("${git.tags}")
    private String tags;

    @Value("${git.commit.id.describe}")
    private String commitIdDescribe;

    @Value("${git.commit.id.describe-short}")
    private String commitIdDescribeShort;

    @Value("${git.commit.id}")
    private String commitId;

    @Value("${git.commit.id.abbrev}")
    private String commitIdAbbrev;

    @Value("#{new java.text.SimpleDateFormat(\"yyyy-MM-dd'T'HH:mm:ssZ\").parse(\"${git.commit.time}\")}")
    private Date commitTime;

    @Value("#{new java.text.SimpleDateFormat(\"yyyy-MM-dd'T'HH:mm:ssZ\").parse(\"${git.build.time}\")}")
    private Date buildTime;

    @Value("${git.build.user.name}")
    private String buildUserName;

    @Value("${git.build.user.email}")
    private String buildUserEmail;

    @Value("${git.commit.message.full}")
    private String commitMessageFull;

    @Value("${git.commit.message.short}")
    private String commitMessageShort;

    @Value("${git.commit.user.name}")
    private String commitUserName;

    @Value("${git.commit.user.email}")
    private String commitUserEmail;

    @Value("${git.remote.origin.url}")
    private String remoteOriginUrl;
}
