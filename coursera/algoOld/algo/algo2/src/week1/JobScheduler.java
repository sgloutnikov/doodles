package week1;


import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JobScheduler {

    List<Job> diffJobs = new ArrayList<Job>();
    List<Job> ratioJobs = new ArrayList<Job>();


    public void runScheduler(List<Job> jobs) {
        Collections.sort(jobs, new Comparator<Job>() {
            @Override
            public int compare(Job job1, Job job2) {
                //TODO: Clean up with ternary operator
                if (job1.order > job2.order)
                    return -1;
                else if (job1.order < job2.order)
                    return 1;
                else {
                    if (job1.weight > job2.weight)
                        return -1;
                    else if (job1.weight < job2.weight)
                        return 1;
                    else
                        return 0;
                }
            }
        });
    }

    public long getCompletionTime(List<Job> jobs) {
        long totalCompletionTime = 0;
        long completionTime = 0;
        for (Job job : jobs) {
            completionTime += job.lenghth;
            job.completionTime = job.weight * completionTime;
            totalCompletionTime += job.completionTime;
        }
        return totalCompletionTime;
    }

    public void computeDiffOrder() {
        for (Job j : diffJobs) {
            j.order = j.weight - j.lenghth;
        }
    }

    public void computeRatioOrder() {
        for (Job j : ratioJobs) {
            j.order = (double)j.weight/j.lenghth;
        }
    }

    public static void main(String[] args) throws IOException {
        JobScheduler js = new JobScheduler();

        URL url = JobScheduler.class.getResource("jobs.txt");
        File f = new File(url.getPath());
        BufferedReader br = new BufferedReader(new FileReader(f));


        int numJobs = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < numJobs; i++) {
            String[] jobsString = br.readLine().split("\\s+");
            int w = Integer.parseInt(jobsString[0]);
            int l = Integer.parseInt(jobsString[1]);
            Job j = new Job(w, l);
            js.diffJobs.add(j);
            js.ratioJobs.add(j);
        }

        js.computeDiffOrder();
        js.runScheduler(js.diffJobs);
        System.out.println(js.getCompletionTime(js.diffJobs));
        js.computeRatioOrder();
        js.runScheduler(js.ratioJobs);
        System.out.println(js.getCompletionTime(js.ratioJobs));

    }
}
