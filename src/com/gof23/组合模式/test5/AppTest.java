package com.gof23.组合模式.test5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class AppTest {

    public static void main(String[] args) {
        CompanyComponents d = new Dept("开发部","项目开发中心",0);
        CompanyComponents d1 = new Dept("技术部","代码功能开发",1);
        CompanyComponents d2 = new Dept("测试部","测试代码功能",2);
        CompanyComponents d3 = new Dept("实施部","项目实地实施",3);

        CompanyComponents e = new Emp("张三",0,6000);
        CompanyComponents e1 = new Emp("李四",1,5000);
        CompanyComponents e2 = new Emp("王五",2,6000);
        CompanyComponents e3 = new Emp("赵六",3,4500);
        CompanyComponents e4 = new Emp("田七",4,4000);
        CompanyComponents e5 = new Emp("崔八",5,3000);
        CompanyComponents e6 = new Emp("董大",6,3000);

        d.add(d1);
        d.add(d2);
        d.add(d3);
        d1.add(e);
        d1.add(e1);
        d1.add(e2);
        d1.add(e3);
        d2.add(e4);
        d3.add(e5);
        d3.add(e6);

//        d.print("");
//        Dept.printS(d);
        CompanyIterator iterator = ((Dept) d).iterator();
        while (iterator.hasNext()) {
            CompanyComponents next = iterator.next();
            System.out.println(next.getName());
        }
    }
}


abstract class CompanyComponents {
    private int id;

    private String name;

    public CompanyComponents(String name, int id) {
        this.id = id;
        this.name = name;
    }

    // 共有的方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // 部门的方法
    public void add(CompanyComponents cc) {
        // 不支持操作异常
        throw new UnsupportedOperationException();
    }

    public int remove(CompanyComponents cc) {
        throw new UnsupportedOperationException();
    }

    public CompanyComponents get(int id) {
        throw new UnsupportedOperationException();
    }

    public String getDescription() {
        throw new UnsupportedOperationException();
    }

    public void setDescription(String description) {
        throw new UnsupportedOperationException();
    }

    public List<CompanyComponents> getClist() {
        throw new UnsupportedOperationException();
    }
    // 员工的方法
    public int getSal() {
        throw new UnsupportedOperationException();
    }

    // 员工部门共有的方法
    public abstract void print(String prefix);
}

class Dept extends CompanyComponents {

    private String description;
    private List<CompanyComponents> clist = new ArrayList<>();

    public Dept(String name, String description, int id) {
        super(name, id);
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void print(String prefix) {
        System.out.println(prefix + this.getName() + ":" + this.getDescription());
        Iterator<CompanyComponents> iterator = clist.iterator();
        while (iterator.hasNext()) {
            CompanyComponents next = iterator.next();
            next.print(prefix + "\t");
        }
    }

    @Override
    public void add(CompanyComponents cc) {
        clist.add(cc);
    }

    @Override
    public int remove(CompanyComponents cc) {
        int id = cc.getId();
        if (clist.remove(cc)) {
            return id;
        } else {
            return -1;
        }
    }

    @Override
    public CompanyComponents get(int id) {
        return clist.get(id);
    }

    @Override
    public List<CompanyComponents> getClist() {
        return clist;
    }

    public static void printS(CompanyComponents dept) {
        Iterator<CompanyComponents> iterator = dept.getClist().iterator();
        while (iterator.hasNext()) {
            CompanyComponents next = iterator.next();
            if (next instanceof Emp) {
                if (next.getSal() > 4000) {
                    next.print("");
                }
            } else {
                printS((Dept) next);
            }
        }
    }

    public CompanyIterator iterator() {
        return new CompanyIterator(clist.iterator());
    }
}

class Emp extends CompanyComponents {

    private int sal;

    public Emp(String name, int id, int sal) {
        super(name, id);
        this.sal = sal;
    }

    @Override
    public int getSal() {
        return sal;
    }

    @Override
    public void print(String prefix) {
        System.out.println(prefix + this.getName() + ":" + this.getSal());
    }
}

class CompanyIterator implements Iterator<CompanyComponents> {
    private Stack<Iterator<CompanyComponents>> s = new Stack<>();

    public CompanyIterator(Iterator<CompanyComponents> it) {
        s.push(it);
    }

    @Override
    public boolean hasNext() {
        if (s.isEmpty()) {
            return false;
        } else {
            Iterator<CompanyComponents> it = s.peek();
            if (!it.hasNext()) {
                s.pop();
                return hasNext();
            } else {
                return true;
            }
        }
    }

    @Override
    public CompanyComponents next() {
        Iterator<CompanyComponents> it = s.peek();
        // 调用的是ArrayList的next方法
        CompanyComponents cc = it.next();
        if (cc instanceof Dept) {
            s.push(cc.getClist().iterator());
        }
        return cc;
    }
}