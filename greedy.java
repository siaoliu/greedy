package networks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class greedy {

	public Vector greedy_node(HashMap sign) {//sign 表示那个临接矩阵
		/*
		 *完善代码需要包括以下几个部分：
		 * 1.计算某个点对该图的影响力
		 * 2.返回失去某个点后的临接矩阵，贪心算法每次失去一个点后，图就发生了变化
		 * 就是在Effect下面添加calculateEffect函数、deleteNode函数
		 */
		// TODO 自动生成的方法存根
		double max_suminfo = 0;
		Vector best_node = new Vector();
		
		ReadFile test = new ReadFile();
		test.load(".\\links.txt");
		int num_of_node = test.max; //节点的总个数
		int iter = 0; //循环次数
		Effect ef = new Effect();
		while(iter < 5000) { 
		//这里只循环5000次，每次得出10个节点数，我们最终统计出现频率最高的那十个节点就是要的结果
			double suminfo = 0;
			HashMap my_sign = (HashMap) sign.clone();
			Vector init_node = new Vector();
			//1.计算出那个节点效果最佳
			for(int k = 0;k<10;k++) {
				double max_info = 0;//设置最初影响力为0
				int maxnode_index = -1;
				for(int i = 0 ; i <= num_of_node ; i++) {
					if (init_node.contains(i)==false){//如果暂时未删除该节点，计算一下
						double i_info = ef.calculateEffect(my_sign,i);//计算某个节点的影响力
						if(i_info > max_info) {//配合for循环找到影响最大的那个函数
							max_info = i_info;
							maxnode_index = i;
						}
					}
				}
			//上诉代码已经找到了当前网络某个影响力最大的节点maxnode_index,将该节点加入删除点集合，并从图中去除该点的影响点
				init_node.add(maxnode_index);
				suminfo += max_info;
				//10次循环之后，init_node 就是我们需要的10个点，而sum_info是这十个点的影响力之和
				my_sign = ef.deleteNode(my_sign,maxnode_index);//每次删除一个节点，图会变化一次
			}
			
			/*
			 * 运行到这里，已经完成了一大部分，10个点已经找了出来
			 * 我们可以通过5000次循环找10个点寻求最优秀的情况
			 * 我想了一下，可以用删去10节点影响力和最大的那一个
			 * 你有其余思路我能帮你修改这一块
			 * 
			 */
			if(suminfo>max_suminfo) {
				max_suminfo = max_suminfo;
				best_node = init_node;
			}
		}
		return best_node;
	}

}
