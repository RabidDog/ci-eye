package org.netmelody.cieye.spies.jenkins.test;

import java.io.File;

import org.junit.Test;
import org.netmelody.cieye.core.observation.Contact;
import org.netmelody.cieye.server.configuration.RecordedKnownOffenders;
import org.netmelody.cieye.server.configuration.SettingsFile;
import org.netmelody.cieye.server.observation.protocol.JsonRestRequester;
import org.netmelody.cieye.spies.jenkins.JenkinsCommunicator;
import org.netmelody.cieye.spies.jenkins.JobLaboratory;
import org.netmelody.cieye.spies.jenkins.jsondomain.Job;

import com.google.gson.GsonBuilder;

public final class JobLaboratoryTest {

    @Test public void
    canPullFromTheJenkinsLiveInstance() {
        final Contact contact = new JsonRestRequester(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create());
        final JenkinsCommunicator communicator = new JenkinsCommunicator("http://ci.jenkins-ci.org", "", "", contact);
        final JobLaboratory lab = new JobLaboratory(communicator, new RecordedKnownOffenders(new SettingsFile(new File(""))));

        final Job job = new Job();
        job.url = "http://ci.jenkins-ci.org/view/Jenkins%20core/job/jenkins_pom/";
        
        lab.analyseJob(job);
        lab.lastBadBuildUrlFor(job);
    }
}
